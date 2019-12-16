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
                    <form method="post" name="new_poll" class="needs-validation" action="/inseriscipoll" novalidate>
                        <div class="row">
                            <div class="col-md-12">
                                <label for="validationCustom01">Titolo Sondaggio</label>
                                <div class="col-md-12 form-input">
                                    <input type="text" class="form-control" name="nomedomanda"  id="validationCustom01" placeholder="Titolo del sondaggio" required>
                                    <div class="invalid-feedback">
                                        Inserisci un titolo per il sondaggio
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-12">
                                <label for="validationCustom02">Testo di apertura</label>
                                <div class="col-md-12 form-input">
                                    <textarea id="validationCustom02" type="text" class="form-control noresize" name="testoapertura" placeholder="Descrizione sondaggio" rows="4" required></textarea>
                                    <div class="invalid-feedback">
                                        Inserisci testo di apertura
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-12">
                                <label for="validationCustom03">Testo di chiusura</label>
                                <div class="col-md-12 form-input">
                                    <input type="text" class="form-control" id="validationCustom03" name="testochiusura" placeholder="Testo di fine compilazione del sondaggio" required />
                                    <div class="invalid-feedback">
                                        Inserisci testo di chiusura
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-12">
                                <label>Privacy</label>
                                <div class="form-check ml-3">
                                    <input class="form-check-input" type="radio" name="statuspoll" id="exampleRadios1" value="1" required checked>
                                    <label class="form-check-label pl-3" for="exampleRadios1">
                                        Pubblico
                                    </label>
                                </div>
                                <div class="form-check ml-3">
                                    <input class="form-check-input" type="radio" name="statuspoll" id="exampleRadios2" value="0" required>
                                    <label class="form-check-label pl-3" for="exampleRadios2">
                                        Privato
                                    </label>
                                </div>
                            </div>
                            <div class="col-md-12">
                                <div class="col-md-12 form-btn text-center nopadding" style="margin-top: 20px;">
                                    <input type="submit" value="Crea Sondaggio" class="btn btn-block btn-secondary btn-red" />
                                </div>
                            </div>
                        </div>
                    </form>
                    <script>
                        (function() {
                            'use strict';
                            window.addEventListener('load', function() {
                                var forms = document.getElementsByClassName('needs-validation');
                                var validation = Array.prototype.filter.call(forms, function(form) {
                                    form.addEventListener('submit', function(event) {
                                        if (form.checkValidity() === false) {
                                            event.preventDefault();
                                            event.stopPropagation();
                                        }
                                        form.classList.add('was-validated');
                                    }, false);
                                });
                            }, false);
                        })();
                    </script>
                </div>
            </div>
        </div>
    </div>
</section>