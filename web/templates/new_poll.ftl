<section id="gtco-contact-form" class="bg-white pt-5">
    <div class="container">
        <div class="section-content">
            <!-- Section Title -->
            <div class="title-wrap">
                <h2 class="section-title">Crea un nuovo sondaggio</h2>
                <p class="section-sub-title">Riempi i campi per creare un sondaggio.<br>Quando hai finito premi il bottone per creare il sondaggio ed aggiungere domande</p>

            </div>
            <!-- End of Section Title -->

            <div class="flex-row justify-content-center align-items-center">
                <!-- Contact Form Holder -->
                <div class="flex-column justify-content-center pd-h">
                    <form method="post" name="contact-us" action="">
                        <div class="row">

                            <div class="col-md-12">
                                <label>Titolo Sondaggio</label>
                                <div class="col-md-12 form-input">
                                    <input type="text" class="form-control" id="subject" name="subject" placeholder="Titolo del sondaggio">
                                </div>
                            </div>
                            <div class="col-md-12">
                                <label>Testo di apertura</label>
                                <div class="col-md-12 form-input">
                                    <input type="text" class="form-control" id="subject" name="subject" placeholder="Descrizione sondaggio">
                                </div>
                            </div>
                            <div class="col-md-12">
                                <label>Testo di chiusura</label>
                                <div class="col-md-12 form-input">
                                    <input type="text" class="form-control" id="subject" name="subject" placeholder="Testo di fine compilazione del sondaggio">
                                </div>
                            </div>
                            <div class="col-md-12">
                                <label>Privacy</label>
                                <div class="form-check ml-3">
                                    <input class="form-check-input" type="radio" name="exampleRadios" id="exampleRadios1" value="option1" checked>
                                    <label class="form-check-label pl-3" for="exampleRadios1">
                                        Pubblico
                                    </label>
                                </div>
                                <div class="form-check ml-3">
                                    <input class="form-check-input" type="radio" name="exampleRadios" id="exampleRadios2" value="option2">
                                    <label class="form-check-label pl-3" for="exampleRadios2">
                                        Privato
                                    </label>
                                </div>
                            </div>
                            <div class="col-md-12 form-btn text-center" style="margin-top: 20px;">
                                <a class="btn btn-block btn-secondary btn-red" href="#">Crea Sondaggio</a>
                            </div>
                        </div>
                    </form>
                    <div id="form-message-warning"></div>
                    <div id="form-message-success">
                        Il tuo sondaggio è stato creato, grazie!
                    </div>
                </div>
                <!-- End of Contact Form Holder -->
            </div>
        </div>
    </div>
</section>