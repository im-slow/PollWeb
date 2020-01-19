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
                                            <#if (pollDB.statePoll == 1)>
                                                <a href="/rispondisondaggio?id=${pollDB.ID}"><h6><small>IN CORSO</small></h6></a>
                                            <#elseif (pollDB.statePoll == 0)>
                                                <a href="/rispondisondaggio?id=${pollDB.ID}"><h6><small>IN FASE DI CREAZIONE</small></h6></a>
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
                <div class="d-flex justify-content-center pt-5 border-top">
                    <h4>Crea un nuovo responsabile</h4>
                </div>
                    <div class="d-flex justify-content-center pt-2 pb-5">Premendo il bottone verrà aggiunto il responsabile al sistema, e riceverà un email contente le credenziali di accesso</div>
                    <div class="container">
                    <form method="post" name="new_res" action="/aggiungires">
                        <div class="form-group row">
                            <label for="email" class="col-sm-2 col-form-label">Email</label>
                            <div class="col-sm-10">
                                <input type="text" pattern="[a-zA-Z0-9!#$%&'*+\/=?^_`{|}~.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*" class="form-control email-js" id="email" name="email" placeholder="name@example.com" required>
                            </div>
                        </div>
                        <div class="d-flex justify-content-end">
                            <input type="submit" class="btn btn-red a-click new-res-js">
                        </div>
                        </form>
                </div>
                </#if>
            </div>
        </div>
    </div>
</section>