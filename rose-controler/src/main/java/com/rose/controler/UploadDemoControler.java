package com.rose.controler;

import com.rose.common.exception.BusinessException;
import com.rose.common.util.StringUtil;
import com.rose.common.util.excel.ExcelUtil;
import com.rose.common.util.excel.HssfExcelUtil;
import com.rose.data.to.vo.ExcelExportVo;
import com.rose.data.to.vo.ExcelImportVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.Arrays;
import java.util.List;

/**
 * 功能：上传下载 demo controller
 * @author sunpeng
 * @date 2018
 */
@Slf4j
@Controller
@RequestMapping("/user/uploadDemo")
public class UploadDemoControler {

    /**
     * 功能：跳转上传页面
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/toUploadDemo")
    public String toUploadDemo() throws Exception {
        return "menu/uploadDemo";
    }

    /**
     * 功能：传统文件上传
     * @param myfile
     * @return
     * @throws IOException
     */
    @ResponseBody
    @PostMapping("uploadFileImg")
    public void uploadFileImg(@RequestParam(value = "myfile", required = false) MultipartFile myfile,
                                        @RequestParam(value = "param1", required = false) String param1,
                                        @RequestParam(value = "param2", required = false) String param2) throws IOException {
    }

    /**
     * 功能：Excel 文件导入
     * @param myfile
     * @return
     * @throws IOException
     */
    @ResponseBody
    @PostMapping("uploadFileExcel")
    public void uploadFileExcel(@RequestParam(value = "myfile") MultipartFile myfile) throws IOException {
        try {
            if (myfile == null) {
                throw new BusinessException("请上传文件！");
            }
            String fileExt = StringUtil.getFileExt(myfile.getOriginalFilename());
            if (!Arrays.asList(".xls", ".xlsx").contains(fileExt)) {
                throw new BusinessException("所上传文件属性错误！");
            }
            InputStream is = myfile.getInputStream();
            if (!is.markSupported()) {
                is = new PushbackInputStream(is, 8);
            }
            ExcelUtil excelUtil = ExcelUtil.create(is);
            List<ExcelImportVo> list = excelUtil.readExcel(ExcelImportVo.class);
            for (ExcelImportVo vo : list) {
                log.info("========" + vo.getParam1() + "========" + vo.getParam2() + "========");
            }
        } catch (Exception e) {
            log.error("导入报错：{}", e);
            throw new BusinessException("导入失败！");
        }
    }

    /**
     * 功能：Excel 导出
     * @throws Exception
     */
    @GetMapping(value = "excelExport")
    public void excelExport(HttpServletResponse response,
                            @RequestParam(value = "param1") String param1,
                            @RequestParam(value = "param2") String param2) {
        try {
            List list = Arrays.asList(new ExcelExportVo(param1, param2), new ExcelExportVo(param2, param1));
            ExcelUtil excelUtil = new HssfExcelUtil();
            excelUtil.writeExcel(response, "订单导出", "订单导出", list);
        } catch (Exception e) {
            log.error("导出报错：{}", e);
        }
    }
}