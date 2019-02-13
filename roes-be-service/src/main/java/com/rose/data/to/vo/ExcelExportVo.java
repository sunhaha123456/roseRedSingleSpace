package com.rose.data.to.vo;

import com.rose.common.util.excel.ExcelExport;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 功能：excel 导出 demo vo
 * @author sunpeng
 * @date 2018
 */
@AllArgsConstructor
@Data
public class ExcelExportVo implements Serializable {

    @ExcelExport(name = "标题1")
    private String param1;

    @ExcelExport(name = "标题2")
    private String param2;

    public ExcelExportVo() {

    }
}