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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            case "searchByCondition":
                searchByCondition(request,response);
                break;
            default:
                break;
        }

    }

    /**
     * 按照条件搜索
     * @param request
     * @param response
     */
    private void searchByCondition(HttpServletRequest request, HttpServletResponse response) {
        Map<String,String> map = new HashMap<>(5);
        map.put("title",request.getParameter("title"));
        map.put("beginS",request.getParameter("begin"));
        map.put("endS",request.getParameter("end"));

        //前台获取当前页数索引
        String currentPage = request.getParameter("currentPage");
        int pageIndex = (currentPage == null ? 1 : Integer.parseInt(currentPage));

        List<DishExt> dishExtList = dishService.searchDishByCondition(map);

        request.setAttribute(
                "allDish",
                dishExtList
        );
        request.setAttribute(
                "allDishType",
                dishTypeService.findAllType()
        );
        //分页
        //总页数
        Page page = new Page(dishExtList.size(), pageIndex);
        request.setAttribute("allCount",page.getPageCount());
        //上一页
        request.setAttribute("preIndex",page.getPreIndex());
        //下一页
        request.setAttribute("nextIndex",page.getNextIndex());

        try {
            request.getRequestDispatcher("view/orderdish.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 增加菜品信息
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
     * 按分类查看菜品信息
     * @param request
     * @param response
     */
    private void findByDishType(HttpServletRequest request, HttpServletResponse response) {
        String remarkS = request.getParameter("type");

        request.setAttribute(
                "allDishs",
                dishService.seeDishInfoByType(remarkS)
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
     * 删除菜品信息
     * @param request
     * @param response
     */
    private void delDish(HttpServletRequest request, HttpServletResponse response) {
        String dishIdS = request.getParameter("dishId");

        dishService.delDish(dishIdS);
        try {
            request.getRequestDispatcher("dish?action=findAllDish").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改餐品信息
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

        //判断是否修改过图片
        if( !(su.getFiles().getFile(0).isMissing())) {
            File file = su.getFiles().getFile(0);
            //获取上传的物理路径
            String path = this.getServletContext().getRealPath("/images");
            fileName =getFileName(file.getFileExt());
            String filePath = path + "\\" + fileName;
            file.saveAs(filePath);
        }

        Map<String,String> map = new HashMap<>(10);
        map.put("dishIdS",request.getParameter("dishId"));
        map.put("fileName",fileName);
        map.put("dishName",su.getRequest().getParameter("dishName"));
        map.put("feature",su.getRequest().getParameter("feature"));
        map.put("ingredients",su.getRequest().getParameter("ingredients"));
        map.put("price",su.getRequest().getParameter("price"));
        map.put("typeId",su.getRequest().getParameter("typeId"));
        map.put("remark",su.getRequest().getParameter("remark"));
        dishService.update(map);
    }

    /**
     * 获取文件名
     * @param ext
     * @return
     */
    private String getFileName(String ext){
        return "F_" + System.currentTimeMillis() + "." + ext;
    }

    /**
     * 查询所有菜品
     * @param request
     * @param response
     */
    private void findAllDish(HttpServletRequest request, HttpServletResponse response) {
        String currentPage = request.getParameter("currentPage");
        int pageIndex;
        if (currentPage == null || currentPage == ""){
            pageIndex = 1;
        } else {
            pageIndex = Integer.parseInt(currentPage);
        }

        request.setAttribute(
                "allDishs",
                dishService.findAllDishByRemark("-4",currentPage)
        );
        request.setAttribute(
                "allDishTypes",
                dishTypeService.findAllType()
        );

        //分页
        //总页数
        Page page = new Page(dishService.countAll(),pageIndex);
        request.setAttribute("allCount",page.getPageCount());
        //上一页
        request.setAttribute("preIndex",page.getPreIndex());
        //下一页
        request.setAttribute("nextIndex",page.getNextIndex());

        try {
            request.getRequestDispatcher("view/dishmanage.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除类型信息
     * @param request
     * @param response
     */
    private void delDishType(HttpServletRequest request, HttpServletResponse response) {
        String typeId = request.getParameter("id");

        dishTypeService.del(typeId);
        try {
            request.getRequestDispatcher("dish?action=dishTypeManage").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改类型信息
     * @param request
     * @param response
     */
    private void updateDishType(HttpServletRequest request, HttpServletResponse response) {
        String typeName = request.getParameter("typeName");
        String typeId = request.getParameter("id");

        dishTypeService.update(typeId,typeName);
        try {
            request.getRequestDispatcher("dish?action=dishTypeManage").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 增加菜品类型
     * @param request
     * @param response
     */
    private void addDishType(HttpServletRequest request, HttpServletResponse response) {
        String typeName = request.getParameter("typeName");
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
     * 查询所有菜品类型
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
     * 分页显示所有菜以及类别
     * @param request
     * @param response
     */
    private void showGeneralUserIndex(HttpServletRequest request, HttpServletResponse response) {
        //前台当前页数索引
        String currentPage = request.getParameter("currentPage");
        //remark用于判断搜索范围。热点，特价，推荐，类型，全部菜品....
        String remarkS = request.getParameter("remark");

        int pageIndex;
        if (currentPage == null || currentPage == ""){
            pageIndex = 1;
        }else {
            pageIndex = Integer.parseInt(currentPage);
        }

        int remark;
        if (remarkS == null || remarkS == ""){
            remark = -4;
        } else {
            //清除购物车为空的提醒
            request.removeAttribute("noOrderCarError");
            remark = Integer.parseInt(remarkS);
        }

        request.setAttribute(
                "allDish",
                dishService.findAllDishByRemark(remarkS, currentPage)
        );
        request.setAttribute(
                "allDishType",
                dishTypeService.findAllType()
        );

        //分页
        //总页数
        Page page = new Page(dishService.countAllByTypeId(remark),pageIndex);
        request.setAttribute("allCount",page.getPageCount());
        //上一页
        request.setAttribute("preIndex",page.getPreIndex());
        //下一页
        request.setAttribute("nextIndex",page.getNextIndex());

        try {
            request.getRequestDispatcher("view/orderdish.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * 展示菜品详情
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
     * 显示首页
     * @param request
     * @param response
     */
    private void indexShowList(HttpServletRequest request, HttpServletResponse response) {
        //热点菜品
        request.setAttribute("toDayHotDish",dishService.findAllDishByRemark("-3","1"));
        //今日特价
        request.setAttribute("toDayPriceDish",dishService.findAllDishByRemark("-1","1"));
        //厨师推荐
        request.setAttribute("toDayRecomDish",dishService.findAllDishByRemark("-2","1"));

        try {
            request.getRequestDispatcher("index.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
