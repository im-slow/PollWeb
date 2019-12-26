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
                                        <a href="/rispondisondaggio?id=${pollDB.ID}"><img src="../style/img/card-image.png" alt=""></a>
                                    </div>
                                    <div class="blog-text">
                                        <div class="blog-tag">
                                            <#if (pollDB.pollstatus)>
                                                <a href="/rispondisondaggio?id=${pollDB.ID}"><h6><small>IN CORSO</small></h6></a>
                                            <#else>
                                                <a href="/rispondisondaggio?id=${pollDB.ID}"><h6><small>CHIUSO</small></h6></a>
                                            </#if>
                                        </div>
                                        <div class="blog-title">
                                            <a href="/rispondisondaggio?id=${pollDB.ID}"><h4>${pollDB.title}</h4></a>
                                        </div>
                                        <div class="blog-meta">
                                            <p class="blog-date">30 May 2016</p> /
                                            <p class="blog-comment">23 Comments</p>
                                        </div>
                                        <div class="blog-desc">
                                            <p>${pollDB.opentext}</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- End of Blog Item -->
                        </#list>
                        <#else>
                            <p>Non sono presenti sondaggi nel database</p>
                        </#if>
                    <div class="col-md-4 blog-item-wrapper d-flex flex-column" style="min-height: 502px;">
                        <div class="blog-item d-flex flex-column justify-content-center align-items-center flex-grow-1">
                            <a class="navbar-brand d-flex align-items-center" style="font-size: 50px !important;" href="/creasondaggio">
                                <span class="lnr lnr-plus-circle"> </span>
                            </a>
                            <div class="blog-desc">
                                <p>Crea sondaggio</p>
                            </div>
                        </div>
                    </div>
                </div>
                <#if role.name == 'ADMIN'>
                <div class="d-flex justify-content-center pt-5 pb-5 border-top">
                    <h4>Crea un nuovo responsabile</h4>
                </div>
                <div class="">
                    <form method="post" name="new_res" action="/profile">
                        <div class="form-group row">
                            <label for="email" class="col-sm-2 col-form-label">Email</label>
                            <div class="col-sm-10">
                                <input type="email" class="form-control" id="email" placeholder="name@example.com" required>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="password" class="col-sm-2 col-form-label">Password</label>
                            <div class="col-sm-10">
                                <input type="password" class="form-control" id="password" placeholder="Password" required>
                            </div>
                        </div>
                        <div class="d-flex justify-content-end">
                            <button type="submit" class="btn btn-red">Registra Responsabile</button>
                        </div>
                    </form>
                </div>
                </#if>
            </div>
        </div>
    </div>
</section>