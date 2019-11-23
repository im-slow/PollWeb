<div class="jumbotron d-flex align-items-center flex-row-reverse" style="background-image: url(../style/img/survey4.png)">
    <div class="d-flex flex-column align-items-center align-text-center">
        <h1 class="display-2 mb-4">PollWeb</h1>
        <p>Rispondi ai nostri sondaggi o creane uno personalizzato.
            <br>Pubblici o privati, a seconda delle tue esigenze.
        </p>
        <div class="col-md-12 form-btn text-center nopadding">
            <a class="btn btn-block btn-secondary btn-red" href="#">Crea Sondaggio</a>
        </div>
    </div>
</div>


<section id="gtco-blog" class="bg-grey">
    <div class="container">
        <div class="section-content">
            <div class="title-wrap mb-5">
                <h2 class="section-title">Sondaggi <b>popolari</b></h2>
                <p class="section-sub-title">Qui sono presenti i sondaggi più seguiti</p>
            </div>
            <div class="row">
                <!-- Blog -->
                <div class="col-md-12 blog-holder">
                    <div class="row">
                        <!-- Blog Item -->
                        <#if (poll?size>0)>
                            <#list poll as pollDB>
                            <div class="col-md-4 blog-item-wrapper">
                                <div class="blog-item">
                                    <div class="blog-img">
                                        <a href="single.html"><img src="../style/img/blog-1.jpg" alt=""></a>
                                    </div>
                                    <div class="blog-text">
                                        <div class="blog-tag">
                                            <#if (pollDB.pollstatus)>
                                            <a href="#"><h6><small>IN CORSO</small></h6></a>
                                            <#else>
                                            <a href="#"><h6><small>CHIUSO</small></h6></a>
                                            </#if>
                                        </div>
                                        <div class="blog-title">
                                            <a href="#"><h4>${pollDB.title}</h4></a>
                                        </div>
                                        <div class="blog-meta">
                                            <p class="blog-date">30 May 2016</p> /
                                            <p class="blog-comment"><a href="">23 Comments</a></p>
                                        </div>
                                        <div class="blog-desc">
                                            <p>${pollDB.opentext}</p>
                                        </div>
                                        <div class="blog-author">
                                            <p>by "${pollDB.user.name}"</p>
                                        </div>
                                        <div class="blog-share-wrapper">
                                            <a class="blog-share" href="google.com">
                                                <i class="fab fa-facebook-square"></i>
                                            </a>
                                            <a class="blog-share" href="google.com">
                                                <i class="fab fa-twitter-square"></i>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- End of Blog Item -->
                            </#list>
                        <#else>
                        <p>Non sono presenti sondaggi nel database</p>
                        </#if>

                    </div>
                </div>
                <!-- End of Blog -->
            </div>
        </div>
    </div>
</section>