<!-- Contact Form Section -->
<section id="gtco-contact-form" class="bg-white">
    <div class="section-content">
        <!-- Section Title -->
        <div class="col justify-content-center align-items-center title-wrap mb-3">
            <h2 class="section-title">Inserisci le domande per il nuovo sondaggio</h2>
            <p class="section-sub-title">Riempi i campi per creare nuove domande.<br>premi + per aggiungerne nuove</p>
        </div>
        <!-- End of Section Title -->
        <form method="post" name="new-questions" class="needs-validation" action="/inseriscidomande" novalidate>
            <div id="accordionExample" class="accordion">
                <div id="card-header-js" class="price-box card col-md-8 offset-md-2 m-t-4">
                    <div class="card-header border-bottom border-top border-left border-right" id="heading1">
                        <div class="row align-items-center align-text-center justify-content-between flex-nowrap px-5 collpase-header-js" data-toggle="collapse" data-target="#collapse1" aria-expanded="true" aria-controls="collapse1">
                            <h6 id="number-js" class="text-orange font-weight-bold mr-2">1</h6>
                            <h6 class="question-name-js">Nuova domanda</h6>
                            <a href="javascript:void(0)" class="text-orange font-weight-bold a-click remove-question-js ml-2"><h3> - </h3></a>
                        </div>
                    </div>
                    <div class="card-body border-bottom border-left border-right collapse show card-body-js" aria-labelledby="heading1" data-parent="#accordionExample" id="collapse1">
                        <div class="col-md-12">
                            <label class="pl-4">Inserici la domanda</label>
                            <div class="col-md-12 form-input">
                                <input type="text" class="form-control question-input-js" id="validationCustom01" name="questname1" placeholder="Inserisci qui la domanda" required>
                                <div class="invalid-feedback">
                                    Il campo non può essere vuoto
                                </div>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <label class="pl-4">Note della domanda</label>
                            <div class="col-md-12 form-input">
                                <input type="text" class="form-control" id="info-js" name="info1" placeholder="Inserisci informazioni adizionali della domanda">
                                <div class="invalid-feedback">
                                    Il campo non può essere vuoto
                                </div>
                            </div>
                        </div>

                        <div class="col-md-12 row pl-5 justify-content-between align-items-center py-2 flex-nowrap">
                            <div class="d-flex flex-row justify-content-center align-items-center">
                                <div class="btn-group p-t-5 pl-4 pb-5">
                                    <button type="button" class="btn btn-light dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        Tipologia
                                    </button>
                                    <div class="dropdown-menu">
                                        <a class="dropdown-item" href="#">Scelta singola</a>
                                        <a class="dropdown-item" href="#">Scelta multipla</a>
                                        <a class="dropdown-item" href="#">Testo Lungo</a>
                                        <a class="dropdown-item" href="#">Testo Breve</a>
                                        <a class="dropdown-item" href="#">Data</a>
                                        <a class="dropdown-item" href="#">Numero</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="border-top pt-5 mx-5"></div>
                        <div class="col-md-12">
                            <div class="d-flex flex-row align-items-center justify-content-center px-4 mt-2 mb-4">
                                <input name="min1" id="min-js" type="number" class="form-control mr-3 form-contro-maxmin" placeholder="minimo risposte multiple *" required>
                                <input name="max1" id="max-js" type="number" class="form-control form-contro-maxmin" placeholder="massimo risposte multiple *" required>
                            </div>
                        </div>
                        <div class="container-fluid">
                            <div class="col-sm-12">
                                <div id="append-answer-js" class="form-group">
                                    <div id="question-option-js" class="d-flex flex-row align-items-center justify-content-between">
                                        <div class="d-flex flex-column align-items-center justify-content-center flex-grow-1">
                                            <input name="domanda1" id="answer-js" type="search" class="form-control" placeholder="Inserisci domanda" value="" required />
                                        </div>
                                        <a href="javascript:void(0)" class="text-orange font-weight-bold a-click remove-question2-js ml-2" style="margin-bottom: 15px;"><h3> - </h3></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-12 d-flex flex-row align-items-center">
                            <div class="col-sm-6 d-flex flex-row align-items-center">
                                <div class="">
                                    <label>Obbligatoria: </label>
                                </div>
                                <div class="form-check form-check-inline pl-4">
                                    <label class="switch">
                                        <input name="isobbligo1" type="checkbox">
                                        <span class="slider round"></span>
                                    </label>
                                </div>
                            </div>
                            <div class="col-sm-6 d-flex flex-row align-items-end justify-content-end">
                                <div id="new-answer-js" class="">
                                    <a href="javascript:void(0)" class="text-orange font-weight-bold a-click"><h3> + </h3></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="d-flex flex-row justify-content-end align-items-center py-5 offset-md-2 m-t-4 col-md-8">
                <a id="another-question-js" class="btn btn-secondary rounded-pill btn-grey a-click"><b>Aggiungi Domanda</b></a>
            </div>
            <div class="d-flex flex-column justify-content-center align-items-stretch py-5 offset-md-2 m-t-4 col-md-8">
                <input type="submit" value="Inserisci" class="btn btn-secondary rounded-pill btn-red a-click" href="/profile" />
            </div>
        </form>
    </div>
</section>

