<section id="gtco-contact-form" class="bg-white pt-5">
    <div class="container">
        <div class="section-content">
            <!-- Section Title -->
            <div class="title-wrap">
                <h2 class="section-title">Accedi</h2>
                <p class="section-sub-title">Effettua il login nel sistema</p>

            </div>
            <!-- End of Section Title -->

            <div class="flex-row justify-content-center align-items-center">
                <!-- Contact Form Holder -->
                <div class="flex-column justify-content-center pd-h">
                    <form method="post" name="contact-us" action="">
                        <div class="row form-group">
                            <div class="col-md-12 nopadding">
                                <label for="exampleInputEmail1">Username/Email</label>
                                <div class="col-md-12 form-input nopadding">
                                    <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name="email" placeholder="Inserisci email">
                                </div>
                            </div>
                            <div class="col-md-12 nopadding">
                                <label>Password</label>
                                <div class="col-md-12 form-input nopadding">
                                    <input type="password" class="form-control" id="password" name="password" placeholder="Inserisci Password">
                                </div>
                            </div>
                            <#if login_error??>
                                <div class="col-md-12 alert alert-danger">
                                    ${login_error}
                                </div>
                            </#if>
                            <div class="col-md-12 form-btn text-center nopadding" style="margin-top: 20px;">
                                <input type="submit" value="Accedi" class="btn btn-block btn-secondary btn-red" href="#" />
                            </div>
                        </div>
                    </form>
                </div>
                <!-- End of Contact Form Holder -->
            </div>
        </div>
    </div>
</section>