/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.InputStream;
import jakarta.servlet.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

@WebServlet(name = "FileUploadServlet", urlPatterns = {"/fileuploadservlet"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)

public class UploadServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private String text;

    private String filename;

    private boolean isSubmitted = false;

    public void setSubmit(boolean isSubmitted) {
        this.isSubmitted = isSubmitted;
    }
    

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            // Get the uploaded file from the request
            Part filePart = request.getPart("file");

            this.filename = filePart.getSubmittedFileName();

            // Read the contents of the file into a string
            InputStream fileInputStream = filePart.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));

            StringBuilder textBuilder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                textBuilder.append(line);
                textBuilder.append("\n");
            }

            int length = textBuilder.length();
            this.text = textBuilder.substring(0, length - 1).toString();
            if (textBuilder.length() != 0) {
                setSubmit(true);
                response.getWriter().write("File uploaded successfully.");
            } else {
                response.getWriter().write("File uploaded failed.");
            }
        } catch (Exception e) {
            response.getWriter().write("File upload failed: " + e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            HuffmanCoding huffman = new HuffmanCoding();
            String button = request.getParameter("button");
            PrintWriter pw = response.getWriter();
            if (this.isSubmitted == true) {
                if ("compress".equals(button)) {
                    // Encode the text using Huffman coding
                    String[] encodedText = huffman.encode(this.text);

                    // You can now do whatever you want with the encoded and decoded texts
                    // For example, you can save them to files or send them as responses
                    response.setContentType("application/msword");
                    response.setHeader("Content-Disposition",
                            "attachment; filename=\""
                            + this.filename.substring(0, this.filename.length() - 4) + "_compressed.txt");

                    pw.println(encodedText[0]);
                } else if ("decompress".equals(button)) {
                    String[] decodedText = huffman.decode(this.text);

                    // You can now do whatever you want with the encoded and decoded texts
                    // For example, you can save them to files or send them as responses
                    response.setContentType("application/msword");
                    response.setHeader("Content-Disposition",
                            "attachment; filename=\""
                            + this.filename.substring(0, this.filename.length() - 4) + "_decompressed.txt");

                    pw.println(decodedText[0]);
                }
            } else if (this.isSubmitted == false) {
                pw.println("File is not submitted!!!");
            }
        } catch (Exception e) {
            response.getWriter().write("Error!!!!");
        }
    }
}
