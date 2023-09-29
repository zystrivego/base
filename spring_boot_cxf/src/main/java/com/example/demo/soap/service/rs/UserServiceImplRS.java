package com.example.demo.soap.service.rs;

import com.example.demo.soap.vo.User;
import io.swagger.annotations.Api;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Api("/UserServiceImplRS")
@Service
public class UserServiceImplRS implements UserServiceRS {

    @Override
    public User findUserById(String id) {
        User user = new User();
        user.setId(id);
        user.setName(id);
        return user;
    }

    @Override
    public User findUserByName(String name) {
        User user = new User();
        user.setId(name);
        user.setName(name);
        return user;
    }

    private static final String FILE_PATH = "D:\\cit.xlsx";

    @Override
    public Response getFile(Long id) throws IOException {
//        File file = new File(FILE_PATH);
//        file.createNewFile();

        Path path = Paths.get(FILE_PATH);
        byte[] content = null;
        try (XSSFWorkbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

            workbook.createSheet("tam");


            workbook.write(outputStream);
            content = outputStream.toByteArray();
        }


//        File file = new File(FILE_PATH);

        Response.ResponseBuilder response = Response.ok(content);
        String download_filename = "cit1.xlsx";
        response.header("Content-Disposition", "attachment;filename= " + download_filename);
        return response.build();
    }
}