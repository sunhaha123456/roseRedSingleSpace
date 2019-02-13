package com.rose.data.to.vo;

import com.rose.common.util.excel.ExcelImport;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 功能：excel 导入 demo vo
 * @author sunpeng
 * @date 2018
 */
@AllArgsConstructor
@Data
public class ExcelImportVo implements Serializable {

    @ExcelImport
    private Integer param1;

    @ExcelImport
    private Double param2;

    public ExcelImportVo() {

    }
}