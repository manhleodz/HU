<%-- 
    Document   : document
    Created on : Dec 15, 2023, 7:35:13 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>

        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/unicons.css">
        <link rel="stylesheet" href="css/owl.carousel.min.css">
        <link rel="stylesheet" href="css/owl.theme.default.min.css">

        <!-- MAIN STYLE -->
        <link rel="stylesheet" href="css/tooplate-style.css">
        <link rel="icon" href="https://cdn-icons-png.flaticon.com/512/3426/3426679.png">
    </head>

    <body>
        <!-- MENU -->
        <nav class="navbar navbar-expand-sm navbar-light">
            <div class="container">
                <a class="navbar-brand" href="/WebApplication1"><img alt="avatar"
                                                                     src="https://cdn-icons-png.flaticon.com/512/3426/3426679.png" style="width: 50px;" /></i> Huffman
                    Coding</a>

                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                        aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                    <span class="navbar-toggler-icon"></span>
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav mx-auto">
                        <li class="nav-item">
                            <a href="/WebApplication1" class="nav-link"><span data-hover="Home">Home</span></a>
                        </li>
                        <li class="nav-item">
                            <a href="/WebApplication1/product.html" class="nav-link"><span data-hover="Product">Product</span></a>
                        </li>
                        <li class="nav-item">
                            <a href="/WebApplication1/document.jsp" class="nav-link"><span data-hover="Document">Document</span></a>
                        </li>
                        <li class="nav-item">
                            <a href="/WebApplication1/draw.html" class="nav-link"><span data-hover="Draw">Draw</span></a> 
                        </li>
                    </ul>

                    <ul class="navbar-nav ml-lg-auto">
                        <div class="ml-lg-4">
                            <div class="color-mode d-lg-flex justify-content-center align-items-center">
                                <i class="color-mode-icon"></i>
                                Color mode
                            </div>
                        </div>
                    </ul>
                </div>
            </div>
        </nav>
        <div class="slide">
            <iframe
                src="https://onedrive.live.com/embed?resid=A1DB3764FAB18139%21115&amp;authkey=!AMt6ALyojJe6olY&amp;em=2&amp;wdAr=1.7777777777777777"
                width="80%" height="700px" frameborder="0">
                Đây là tài liệu
                <a target="_blank" href="https://office.com">
                    Microsoft Office</a> được nhúng, hoạt động trên nền tảng <a target="_blank"
                                                                             href="https://office.com/webapps">Office
                </a>.
            </iframe>
        </div>
        <footer class="footer py-5">
            <div class="container">
                <div class="row">

                    <div class="col-lg-12 col-12">
                        <p class="copyright-text text-center">Copyright &copy; 2023 Group 8 DSA . All rights reserved</p>
                        <p class="copyright-text text-center">Designed by <a rel="nofollow"
                                                                             href="https://www.facebook.com/tooplate">Tooplate</a></p>
                    </div>

                </div>
            </div>
        </footer>

        <script src="js/jquery-3.3.1.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/Headroom.js"></script>
        <script src="js/jQuery.headroom.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/smoothscroll.js"></script>
        <script src="js/custom.js"></script>
    </body>

    <style>
        .slide {
            width: 100%;
            display: flex;
            justify-content: center;
            margin-top: 4rem;
        }
    </style>

</html>