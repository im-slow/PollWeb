<section id="gtco-contact-form" class="bg-white pt-5">
    <div class="container">
        <div class="section-content">
            <!-- Section Title -->
            <div class="title-wrap">
                <h2 class="section-title">Benvenuto, ${user.name}</h2>
                <p class="section-sub-title">Controlla i tuoi sondaggi</p>
            </div>
            <!-- End of Section Title -->
        </div>
        <div class="row">
            <!-- Blog -->
            <div class="col-md-12 blog-holder">
                <div class="row">
                    <!-- Blog Item -->
                    <#if (userPoll?size>0)>
                        <#list userPoll as pollDB>
                            <div class="col-md-4 blog-item-wrapper d-flex flex-column">
                                <div class="blog-item d-flex flex-column flex-grow-1">
                                    <div class="blog-img">
                                        <a href="single.html"><img src="../style/img/card-image.png" alt=""></a>
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
                    <div class="col-md-4 blog-item-wrapper d-flex flex-column">
                        <div class="blog-item d-flex flex-column justify-content-center align-items-center flex-grow-1">
                            <a class="navbar-brand d-flex align-items-center" style="font-size: 50px !important;" href="/creasondaggio">
                                <span class="lnr lnr-plus-circle"></span>
                            </a>
                            <div class="blog-desc">
                                <p>Crea sondaggio</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>