package main.java.controller.servlet;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.model.bean.Brand;
import main.java.model.bean.Category;
import main.java.model.bean.Product;
import main.java.model.bean.ProductImage;
import main.java.model.bean.Segment;
import main.java.model.dao.ProductImageDAO;
import main.java.model.util.ImageUtil;

import main.java.model.util.Page;

@WebServlet("/productImageServlet")
public class ProductImageServlet extends BaseBackServlet {

    @Override
    public String add(HttpServletRequest request, HttpServletResponse response, Page page) {
        // Input stream for uploading files
        InputStream is = null;
        // Additional parameters when submitting the uploaded file
        Map<String, String> params = new HashMap<>();

        // Parse upload
        is = parseUpload(request, params);

        // Generate a productImage object based on the uploaded parameters
        String type = params.get("type");
        int pid = Integer.parseInt(params.get("pid"));
        Product p = productDAO.get(pid);

        ProductImage pi = new ProductImage();
        pi.setType(type);
        pi.setProduct(p);
        productImageDAO.add(pi);

        // Generate file
        String fileName = pi.getId() + ".jpg";
        String imageFolder;
        String imageFolderSmall = null;
        String imageFolderMiddle = null;
        if (ProductImageDAO.type_single.equals(pi.getType())) {
            imageFolder = request.getSession().getServletContext()
                    .getRealPath("img/productSingle");
            imageFolderSmall = request.getSession().getServletContext()
                    .getRealPath("img/productSingle_small");
            imageFolderMiddle = request.getSession().getServletContext()
                    .getRealPath("img/productSingle_middle");
        } else {
            imageFolder = request.getSession().getServletContext()
                    .getRealPath("img/productDetail");
        }

        File f = new File(imageFolder, fileName);
        f.getParentFile().mkdirs();

        // Copy file
        try {
            if (is != null && is.available() != 0) {
                try (FileOutputStream fos = new FileOutputStream(f)) {
                    byte b[] = new byte[1024 * 1024];
                    int length = 0;
                    while (-1 != (length = is.read(b))) {
                        fos.write(b, 0, length);
                    }
                    fos.flush();
                    // Save the file to jpg format with the following code
                    BufferedImage img = ImageUtil.change2jpg(f);
                    ImageIO.write(img, "jpg", f);

                    if (ProductImageDAO.type_single.equals(pi.getType())) {
                        File fSmall = new File(imageFolderSmall, fileName);
                        fSmall.getParentFile().mkdirs();
                        File fMiddle = new File(imageFolderMiddle, fileName);
                        fMiddle.getParentFile().mkdirs();

                        ImageUtil.resizeImage(f, 56, 56, fSmall);
                        ImageUtil.resizeImage(f, 217, 190, fMiddle);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "@admin_productImage_list?pid=" + p.getId();
    }

    @Override
    public String delete(HttpServletRequest request, HttpServletResponse response, Page page) {
        int id = Integer.parseInt(request.getParameter("id"));
        ProductImage pi = productImageDAO.get(id);
        productImageDAO.delete(id);

        if (ProductImageDAO.type_single.equals(pi.getType())) {
            String imageFolderSingle = request.getSession().getServletContext()
                    .getRealPath("img/productSingle");
            String imageFolderSmall = request.getSession().getServletContext()
                    .getRealPath("img/productSingle_small");
            String imageFolderMiddle = request.getSession().getServletContext()
                    .getRealPath("img/productSingle_middle");

            File fSingle = new File(imageFolderSingle, pi.getId() + ".jpg");
            fSingle.delete();
            File fSmall = new File(imageFolderSmall, pi.getId() + ".jpg");
            fSmall.delete();
            File fMiddle = new File(imageFolderMiddle, pi.getId() + ".jpg");
            fMiddle.delete();
        } else {
            String imageFolderDetail = request.getSession().getServletContext()
                    .getRealPath("img/productDetail");
            File fDetail = new File(imageFolderDetail, pi.getId() + ".jpg");
            fDetail.delete();
        }

        return "@admin_productImage_list?pid=" + pi.getProduct().getId();
    }

    @Override
    public String edit(HttpServletRequest request, HttpServletResponse response, Page page) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String update(HttpServletRequest request, HttpServletResponse response, Page page) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String list(HttpServletRequest request, HttpServletResponse response, Page page) {
        int pid = Integer.parseInt(request.getParameter("pid"));
        Product p = productDAO.get(pid);
        Brand b = brandDAO.get(p.getBrand().getId());
		Category c = categoryDAO.get(b.getCategory().getId());
		Segment s = segmentDAO.get(c.getSegment().getId());
        List<ProductImage> pisSingle = productImageDAO.list(p, ProductImageDAO.type_single);
        List<ProductImage> pisDetail = productImageDAO.list(p, ProductImageDAO.type_detail);

        request.setAttribute("p", p);
        request.setAttribute("b", b);
		request.setAttribute("c", c);
		request.setAttribute("s", s);
        request.setAttribute("pisSingle", pisSingle);
        request.setAttribute("pisDetail", pisDetail);

        return "admin/listProductImage.jsp";
    }

}
