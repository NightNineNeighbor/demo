package com.example.demo.endpoint

import org.apache.commons.fileupload.servlet.ServletFileUpload
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.servlet.http.HttpServletRequest

@RestController
class FileController {

    @PostMapping("/file")
    fun upload(@RequestParam("file") file: MultipartFile) {
        val reader = BufferedReader(InputStreamReader(file.inputStream), 10)
        var a = reader.readLine()
        while (a != null) {
            println(a)
            a = reader.readLine()
        }
        reader.close()
    }

    @PostMapping("/file-stream")
    fun uploadStream(request: HttpServletRequest) {
        val upload = ServletFileUpload()
        val iter = upload.getItemIterator(request)
        while (iter.hasNext()) {
            val item = iter.next()
            val stream = item.openStream()
            val reader = BufferedReader(InputStreamReader(stream), 10)
            var line = reader.readLine()
            while (line != null) {
                println(line)
                line = reader.readLine()
            }
            reader.close()
        }
    }

}