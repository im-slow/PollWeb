<!-- Contact Form Section -->
<section id="gtco-contact-form" class="bg-white">
        <div class="section-content">
            <!-- Section Title -->
            <div class="col justify-content-center align-items-center title-wrap mb-3">
                <h2 class="section-title">Inserisci le domande per il nuovo sondaggio</h2>
                <p class="section-sub-title">Riempi i campi per creare nuove domande.<br>premi + per aggiungerne nuove</p>
                <div class="d-flex flex-row justify-content-center align-items-center text-center py-2">
                    <p class="mr-2"><b>Aggiungi Domanda</b></p>
                    <a id="another-question-js" class="btn btn-secondary btn-red" href="#"> + </a>
                </div>
            </div>
            <!-- End of Section Title -->
            <div id="new-question-js">
                <div id="card-header-js" class="price-box card col-md-8 offset-md-2 m-t-4">
                    <div class="card-header border-bottom border-top border-left border-right">
                        <div class="row align-items-center align-text-center justify-content-between flex-nowrap px-5">
                          <h6 id="number-js" class="text-orange font-weight-bold">1</h6>
                          <h6 class="question-name-js">Nuova domanda</h6>
                          <a href="#" class="text-orange font-weight-bold remove-question-js"><h3> - </h3></a>
                        </div>
                    </div>
                    <div class="card-body border-bottom">
                        <div class="col-md-12">
                            <label>Inserici la domanda</label>
                            <div class="col-md-12 form-input">
                                <input id="prova" type="text" class="form-control question-input-js" id="subject" name="subject" placeholder="Inserisci qui la domanda">
                            </div>
                        </div>
                        <div class="col-md-12">
                            <label>Note della domanda</label>
                            <div class="col-md-12 form-input">
                                <input type="text" class="form-control" id="subject" name="subject" placeholder="Inserisci informazioni adizionali della domanda">
                            </div>
                        </div>
                        <div class="col-md-12 row pl-5 justify-content-between align-items-center py-2 flex-nowrap">
                            <div>
                                <label>Domanda obbligatoria: </label>
                                <div class="form-check form-check-inline pl-5">
                                  <input class="form-check-input" type="radio" name="exampleRadios" id="exampleRadios1" value="option1" checked>
                                  <label class="form-check-label pl-2" for="exampleRadios1">
                                    SI
                                  </label>
                                </div>
                                <div class="form-check form-check-inline pl-5">
                                  <input class="form-check-input" type="radio" name="exampleRadios" id="exampleRadios2" value="option2">
                                  <label class="form-check-label pl-2" for="exampleRadios2">
                                    NO
                                  </label>
                                </div>
                            </div>
                            <div class="btn-group p-t-5">
                                <button type="button" class="btn btn-red dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    Action
                                </button>
                                <div class="dropdown-menu">
                                    <a class="dropdown-item" href="#">Scelta singola</a>
                                    <a class="dropdown-item" href="#">Scelta multipla</a>
                                    <a class="dropdown-item" href="#">Testo Aperto</a>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="#">Separated link</a>
                                </div>
                            </div>
                         </div>
                     </div>
                </div>
            </div>
        </div>
</section>