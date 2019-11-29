<div class="jumbotron d-flex align-items-center flex-row-reverse" style="background-image: url(../style/img/survey4.png)">
    <div class="d-flex flex-column align-items-center align-text-center">
        <h1 class="display-2 mb-4">PollWeb</h1>
        <p>Rispondi ai nostri sondaggi o creane uno personalizzato.
            <br>Pubblici o privati, a seconda delle tue esigenze.
        </p>
        <div class="col-md-12 form-btn text-center nopadding">
            <a class="btn btn-block btn-secondary btn-red" href="/creasondaggio">Crea Sondaggio</a>
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
                                        <#if (pollDB.pollstatus)>
                                            <a href="/rispondisondaggio"><img src="../style/img/card-image.png" alt=""></a>
                                        <#else>
                                            <a href="#"><img src="../style/img/card-image.png" alt=""></a>
                                        </#if>
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
                    <div class="col-md-12 form-btn text-center nopadding">
                        <div class="d-flex justify-content-center align-items-center pt-5">
                            <a class="btn btn-block btn-secondary btn-red col-md-4" href="/tuttisondaggi">Mostra Tutti</a>
                        </div>
                    </div>
                </div>
                <!-- End of Blog -->
            </div>
        </div>
    </div>
</section>
<!-- Counter Section -->
<section id="gtco-counter" class="overlay bg-fixed">
    <div class="container">
        <div class="section-content">
            <div class="row">
                <!-- Counter Item -->
                <div class="col-md-3 col-sm-6 counter-item">
                    <i class="lnr lnr-users"></i>
                    <span class="number" data-from="0" data-to="34" data-refresh-interval="100"></span>
                    <h4>Utenti</h4>
                </div>
                <!-- End of Counter Item -->
                <!-- Counter Item -->
                <div class="col-md-3 col-sm-6 counter-item">
                    <i class="lnr lnr-briefcase"></i>
                    <span class="number" data-from="0" data-to="32" data-refresh-interval="100">516</span>
                    <h4>Sondaggi</h4>
                </div>
                <!-- End of Counter Item -->
                <!-- Counter Item -->
                <div class="col-md-3 col-sm-6 counter-item">
                    <i class="lnr lnr-heart"></i>
                    <span class="number" data-from="0" data-to="38" data-refresh-interval="100">10292</span>
                    <h4>Risposte ai Sondaggi</h4>
                </div>
                <!-- End of Counter Item -->
                <!-- Counter Item -->
                <div class="col-md-3 col-sm-6 counter-item">
                    <i class="lnr lnr-rocket"></i>
                    <span class="number" data-from="0" data-to="29" data-refresh-interval="100">90</span>
                    <h4>Teresa</h4>
                </div>
                <!-- End of Counter Item -->
            </div>
        </div>
    </div>
</section>
<!-- End of Counter Section -->
