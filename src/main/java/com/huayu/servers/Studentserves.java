package com.huayu.servers;

import bean.classes;
import bean.student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huayu.dao.ClassMapper;
import com.huayu.dao.StudentMapper;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.ClassesKey;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service("studentserves")
public class Studentserves extends ServiceImpl<StudentMapper, student> {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private ClassMapper classMapper;

    public IPage<student> dd(Page<student> page) {
        return studentMapper.dd(page);
    }


    public List<student> selectall() {
        return studentMapper.selectall();
    }

    public List<student> selectid(student student) {
        return studentMapper.selectid(student);
    }


    public List<classes> selectclass() {
        return classMapper.selectall();
    }


    public void insertstu(student student) {
        studentMapper.insert(student);
    }


    public void delete(student student) {
        studentMapper.delete(student);
    }


    public student select(Integer student) {
        return studentMapper.selectdd(student);
    }


    public void update(student student) {
        studentMapper.update(student);
    }

    public List<student> getexcal(InputStream fileInputStream) throws Exception {
        //获取输入流文件来获得excal文件
        HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);

        List<student> list = new ArrayList<>();

        //获取第一个excal文件
        HSSFSheet sht0 = workbook.getSheetAt(0);

        //获取总行数
        int lastRowIndex = sht0.getLastRowNum();

        for (int i = 1; i <= lastRowIndex; i++) {
            //获取当前行
            HSSFRow row = sht0.getRow(i);
            if (row == null) {
                break;
            }
            //获取当前行所有的列数
            short lastCellNum = row.getLastCellNum();
            student student1 = new student();

            for (int j = 0; j < lastCellNum; j++) {
                if (j == 0) {
                    student1.setName(row.getCell(j).getStringCellValue());
                     String a= row.getCell(j).getStringCellValue();
                }
                if (j==1){
                  /*  student1.setId(row.getCell(j).getStringCellValue());*/
                    student1.setSex(row.getCell(j).getStringCellValue());
                }
                if(j==2){
                   student1.setClassno(Integer.parseInt(row.getCell(j).toString().split("\\.")[0]));
                }
                    list.add(student1);
            }
        }
        fileInputStream.close();
        return  list;
    }



}
