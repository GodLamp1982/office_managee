package com.xja.servlet;

import com.jspsmart.upload.File;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.xja.bean.Dish;
import com.xja.bean.DishExt;
import com.xja.bean.DishType;
import com.xja.common.Page;
import com.xja.service.impl.DishServiceImpl;
import com.xja.service.impl.DishTypeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author GodLamp
 * @date 2022/8/13 10:17
 */
@WebServlet("/dish")
public class DishServlet extends HttpServlet {
    DishServiceImpl dishService = new DishServiceImpl();
    DishTypeServiceImpl dishTypeService = new DishTypeServiceImpl();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action){
            case "indexShow":
                indexShowList(request,response);
                break;
            case "dishDetail":
                showDishDetail(request,response);
                try {
                    request.getRequestDispatcher("view/showsingledetail.jsp").forward(request,response);

                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "generalUserIndex":
                showGeneralUserIndex(request,response);
                break;
            case "dishTypeManage":
                findAllDishType(request,response);
                break;
            case "addType":
                addDishType(request,response);
                break;
            case "updateDishType":
                updateDishType(request,response);
                break;
            case "delType":
                delDishType(request,response);
                break;
            case "findAllDish":
                findAllDish(request,response);
                break;
            case "findDishById":
                showDishDetail(request,response);
                request.setAttribute("allDishTypess",dishTypeService.findAllType());
                try {
                    request.getRequestDispatcher("view/updatedishinfo.jsp").forward(request,response);

                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "updateDishInfo":
                try {
                    updateDishInfo(request,response);
                    request.getRequestDispatcher("dish?action=findAllDish").forward(request,response);
                } catch (SmartUploadException e) {
                    e.printStackTrace();
                }
                break;
            case "delDish":
                delDish(request,response);
                break;
            case "findByDishType":
                findByDishType(request,response);
                break;
            case "findAllDishTypessss":
                request.setAttribute("allDishTypess",dishTypeService.findAllType());
                request.getRequestDispatcher("view/adddish.jsp").forward(request,response);
                break;
            case "addDishInfo":
                try {
                    addDishInfo(request,response);
                } catch (SmartUploadException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }

    }

    /**
     * ??????????????????
     * @param request
     * @param response
     */
    private void addDishInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, SmartUploadException, IOException {
        SmartUpload su = new SmartUpload();
        su.initialize(this.getServletConfig(),request,response);
        su.setAllowedFilesList("jpg,png,jpeg");
        su.setMaxFileSize(1024 * 1024 * 5);

        su.upload();

        String fileName = "";

        if( su.getFiles().getFile(0).isMissing() ) {
            return;
        }
        File file = su.getFiles().getFile(0);

        String path = this.getServletContext().getRealPath("/images");
        fileName =getFileName(file.getFileExt());
        String filePath = path + "\\" + fileName;
        file.saveAs(filePath);

        Dish dish = new Dish(
                0,
                su.getRequest().getParameter("dishName"),
                su.getRequest().getParameter("feature"),
                su.getRequest().getParameter("ingredients"),
                Integer.parseInt(su.getRequest().getParameter("price")),
                Integer.parseInt(su.getRequest().getParameter("typeId")),
                fileName,
                0,
                Integer.parseInt(su.getRequest().getParameter("remark"))
        );

        dishService.add(dish);
        request.getRequestDispatcher("dish?action=findAllDish").forward(request,response);
    }

    /**
     * ???????????????????????????
     * @param request
     * @param response
     */
    private void findByDishType(HttpServletRequest request, HttpServletResponse response) {
        String remarkS = request.getParameter("type");
        if (remarkS == null){
            return;
        }

        request.setAttribute(
                "allDishs",
                dishService.findAllDishByRemark(Integer.parseInt(remarkS),1)
        );
        request.setAttribute(
                "allDishTypes",
                dishTypeService.findAllType()
        );
        try {
            request.getRequestDispatcher("view/dishmanage.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ??????????????????
     * @param request
     * @param response
     */
    private void delDish(HttpServletRequest request, HttpServletResponse response) {
        String dishIdS = request.getParameter("dishId");
        if (dishIdS == null){
            return;
        }
        dishService.delDish(Integer.parseInt(dishIdS));
        try {
            request.getRequestDispatcher("dish?action=findAllDish").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ??????????????????
     * @param request
     * @param response
     */
    private void updateDishInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, SmartUploadException, IOException {
        SmartUpload su = new SmartUpload();
        su.initialize(this.getServletConfig(),request,response);
        su.setAllowedFilesList("jpg,png,jpeg");
        su.setMaxFileSize(1024 * 1024 * 5);

        su.upload();

        String fileName = "";

        //???????????????????????????
        if( !(su.getFiles().getFile(0).isMissing())) {
            File file = su.getFiles().getFile(0);
            //???????????????????????????
            String path = this.getServletContext().getRealPath("/images");
            fileName =getFileName(file.getFileExt());
            String filePath = path + "\\" + fileName;
            file.saveAs(filePath);
        }

        String dishIdS = request.getParameter("dishId");
        System.out.println(dishIdS);//
        if (dishIdS == null){
            System.out.println("n1");//
            return;
        }

        DishExt oldDish = dishService.findByDishId(
                Integer.parseInt(dishIdS)
        );
        if (oldDish == null){
            System.out.println("n2");//
            return;
        }

        if ("".equals(fileName)){
            fileName = oldDish.getDish().getPhoto();
        }

        Dish dish = new Dish(
                oldDish.getDish().getDishId(),
                su.getRequest().getParameter("dishName"),
                su.getRequest().getParameter("feature"),
                su.getRequest().getParameter("ingredients"),
                Integer.parseInt(su.getRequest().getParameter("price")),
                Integer.parseInt(su.getRequest().getParameter("typeId")),
                fileName,
                oldDish.getDish().getClickRote(),
                Integer.parseInt(su.getRequest().getParameter("remark"))
        );

        dishService.update(dish);
    }

    /**
     * ???????????????
     * @param ext
     * @return
     */
    private String getFileName(String ext){
        return "F_" + System.currentTimeMillis() + "." + ext;
    }

    /**
     * ??????????????????
     * @param request
     * @param response
     */
    private void findAllDish(HttpServletRequest request, HttpServletResponse response) {
        String currentPage = request.getParameter("currentPage");
        int pageIndex = (currentPage == null ? 1 : Integer.parseInt(currentPage));
//??????
        //?????????
        int countAll = (int) Math.ceil( (dishService.countAll() ) *1.0 / Page.PAGE_NUMBER);
        request.setAttribute("allCount",countAll);
        //?????????
        request.setAttribute("preIndex",pageIndex > 1 ? (pageIndex - 1) : 1);
        //?????????
        request.setAttribute("nextIndex",pageIndex < countAll ? (pageIndex + 1) : countAll);


        request.setAttribute(
                "allDishs",
                dishService.findAllDishByRemark(-4,pageIndex)
        );
        request.setAttribute(
                "allDishTypes",
                dishTypeService.findAllType()
        );
        try {
            request.getRequestDispatcher("view/dishmanage.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ??????????????????
     * @param request
     * @param response
     */
    private void delDishType(HttpServletRequest request, HttpServletResponse response) {
        String typeId = request.getParameter("id");
        if (typeId == null){
            return;
        }
        dishTypeService.del(Integer.parseInt(typeId));
        try {
            request.getRequestDispatcher("dish?action=dishTypeManage").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ??????????????????
     * @param request
     * @param response
     */
    private void updateDishType(HttpServletRequest request, HttpServletResponse response) {
        String typeName = request.getParameter("typeName");
        String typeId = request.getParameter("id");
        if (typeName == null || typeId == null){
            return;
        }
        dishTypeService.update(new DishType(
                Integer.parseInt(typeId),
                typeName
        ));
        try {
            request.getRequestDispatcher("dish?action=dishTypeManage").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ??????????????????
     * @param request
     * @param response
     */
    private void addDishType(HttpServletRequest request, HttpServletResponse response) {
        String typeName = request.getParameter("typeName");
        if (typeName == null){
            return;
        }
        dishTypeService.add(typeName);
        try {
            request.getRequestDispatcher("dish?action=dishTypeManage").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ????????????????????????
     * @param request
     * @param response
     */
    private void findAllDishType(HttpServletRequest request, HttpServletResponse response) {
        List<DishType> allType = dishTypeService.findAllType();
        request.setAttribute(
                "dishTypes",
                allType
        );
        List<Integer> integerList = new ArrayList<>();
        for (DishType dishType : allType) {
            integerList.add(dishTypeService.allDishInOneTypeCount(dishType.getTypeId()));
        }

        request.setAttribute("oneTypeAllDish",integerList);
        try {
            request.getRequestDispatcher("view/showdishtype.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ?????????????????????????????????
     * @param request
     * @param response
     */
    private void showGeneralUserIndex(HttpServletRequest request, HttpServletResponse response) {
        String currentPage = request.getParameter("currentPage");
        String remarkS = request.getParameter("remark");

        int pageIndex = (currentPage == null ? 1 : Integer.parseInt(currentPage));
        int remark = ( remarkS == null ? -4 : Integer.parseInt(remarkS));

        request.setAttribute(
                "allDish",
                dishService.findAllDishByRemark(remark, pageIndex)
        );
        request.setAttribute(
                "allDishType",
                dishTypeService.findAllType()
        );

        //??????
        //?????????
        int countAll = (int) Math.ceil((dishService.countAll()) *1.0 / Page.PAGE_NUMBER);
        request.setAttribute("allCount",countAll);
        //?????????
        request.setAttribute("preIndex",pageIndex > 1 ? (pageIndex - 1) : 1);
        //?????????
        request.setAttribute("nextIndex",pageIndex < countAll ? (pageIndex + 1) : countAll);

        try {
            request.getRequestDispatcher("view/orderdish.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * ??????????????????
     * @param request
     * @param response
     */
    private void showDishDetail(HttpServletRequest request, HttpServletResponse response) {
        String dish = request.getParameter("dishId");
        if(dish == null){
            return;
        }

        int dishId = Integer.parseInt(dish);

        dishService.clickRoteDishAddOne(dishId);
        request.setAttribute("dish",dishService.findByDishId(dishId));
    }

    /**
     * ????????????
     * @param request
     * @param response
     */
    private void indexShowList(HttpServletRequest request, HttpServletResponse response) {
        //????????????
        request.setAttribute("toDayHotDish",dishService.findAllDishByRemark(-3,1));
        //????????????
        request.setAttribute("toDayPriceDish",dishService.findAllDishByRemark(-1,1));
        //????????????
        request.setAttribute("toDayRecomDish",dishService.findAllDishByRemark(-2,1));

        try {
            request.getRequestDispatcher("index.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
