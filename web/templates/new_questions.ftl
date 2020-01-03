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
            <label for="questnumbers"></label>
            <input type="text" class="form-control question-input-js" id="questnumbers" name="questnumbers" hidden>
            <input id="pollID-js" type="text" name="idpoll" value="${pollID}" hidden>
            <div id="accordionExample" class="accordion">
                <#if (question?size>0)>
                    <#list question as qst>
                        <div id="card-header-js" class="price-box card col-md-8 offset-md-2 m-t-4">
                            <input type="text" class="proxy-id-js" name=id${qst.position}" value="${qst.ID}" hidden>
                            <input type="text" class="form-control question-input-js questnumber-js" name="numberquest${qst.position}" hidden>
                            <div class="card-header border-bottom border-top border-left border-right" id="heading${qst.position}">
                                <div class="row align-items-center align-text-center justify-content-between flex-nowrap px-5 collpase-header-js" data-toggle="collapse" data-target="#collapse${qst.position}" aria-expanded="true" aria-controls="collapse${qst.position}">
                                    <h6 id="number-js" class="text-orange font-weight-bold mr-2">1</h6>
                                    <h6 class="question-name-js">${qst.questionText}</h6>
                                    <a href="javascript:void(0)" class="text-orange font-weight-bold a-click remove-question-js ml-2"><h3> - </h3></a>
                                </div>
                            </div>
                            <div class="card-body border-bottom border-left border-right collapse show card-body-js" aria-labelledby="heading${qst.position}" data-parent="#accordionExample" id="collapse${qst.position}">
                                <div class="col-md-12">
                                    <label class="pl-4">Inserici la domanda</label>
                                    <div class="col-md-12 form-input">
                                        <input type="text" class="form-control question-input-js" id="validationCustom01" name="questname${qst.position}" placeholder="Inserisci qui la domanda" value="${qst.questionText}" required>
                                        <div class="invalid-feedback">
                                            Il campo non può essere vuoto
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <label class="pl-4">Note della domanda</label>
                                    <div class="col-md-12 form-input">
                                        <input type="text" class="form-control info-js" name="info${qst.position}" placeholder="Inserisci informazioni adizionali della domanda" value="${qst.note}">
                                        <div class="invalid-feedback">
                                            Il campo non può essere vuoto
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-12 row pl-5 justify-content-between align-items-center py-2 flex-nowrap mb-4">
                                    <div class="col-md-9 d-flex flex-row justify-content-center align-items-center">
                                        <label class="mr-3 label-tipology-js" for="exampleFormControlSelect${qst.position}" style="white-space: nowrap">
                                            Seleziona Tipologia:
                                        </label>
                                        <select class="form-control" id="exampleFormControlSelect${qst.position}" style="margin: 0!important">
                                            <option  value="answer" name="SINGLECHOISE" class="hidden-answer-js" selected>Scelta singola</option>
                                            <option  value="all" name="MULTIPLECHOISE" class="hidden-answer-js">Scelta multipla</option>
                                            <option  value="min-max" name="LONGTEXT" class="hidden-answer-js">Testo Lungo</option>
                                            <option  value="min-max" name="SHORTTEXT" class="hidden-answer-js">Testo Breve</option>
                                            <option  value="date" name="DATE" class="hidden-answer-js">Data</option>
                                            <option  value="min-max" name="NUMBER" class="hidden-answer-js">Numero</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="border-top pt-5 mx-5"></div>

                                <div id="answer-type-js">
                                <!-- SINGLE CHOISE -->
                                    <div id="answer-field-js">
                                        <input class="question-type-js" name="questionType1" value="${qst.questionType}" hidden />
                                        <#if qst.minimum ??>
                                            <#if qst.questionType == "DATE">
                                                <div class="col-md-12">
                                                    <div class="d-flex flex-row align-items-center justify-content-center px-4 mt-2 mb-4">
                                                        <input name="min${qst.position}" type="date" class="form-control mr-3 form-contro-maxmin min-js" placeholder="minimo risposte multiple *" value="${qst.minimum}" required>
                                                        <input name="max${qst.position}" type="date" class="form-control form-contro-maxmin max-js" placeholder="massimo risposte multiple *" value="${qst.maximum}" required>
                                                    </div>
                                                </div>
                                            <#else>
                                                <div class="col-md-12">
                                                    <div class="d-flex flex-row align-items-center justify-content-center px-4 mt-2 mb-4">
                                                        <input name="min${qst.position}" type="number" class="form-control mr-3 form-contro-maxmin min-js" placeholder="minimo risposte multiple *" value="${qst.minimum}" required>
                                                        <input name="max${qst.position}" type="number" class="form-control form-contro-maxmin max-js" placeholder="massimo risposte multiple *" value="${qst.maximum}" required>
                                                    </div>
                                                </div>
                                            </#if>
                                        </#if>
                                        <div class="container-fluid">
                                            <div class="col-sm-12">
                                                <div id="append-answer-js" class="form-group">
                                                    <#list qst.QAnswer as answer>
                                                        <#if answer != "">
                                                            <div class="d-flex flex-row align-items-center justify-content-between question-option-js">
                                                                <div class="d-flex flex-column align-items-center justify-content-center flex-grow-1">
                                                                    <input name="domanda${qst.position}" type="search" class="form-control answer-js" placeholder="Inserisci domanda" value="${answer}" required />
                                                                </div>
                                                                <div class="d-flex flex-column justify-content-center align-items-center align-text-center ml-3 delete_option_quest">
                                                                    <a href="javascript:void(0)" class="text-orange font-weight-bold a-click remove-question2-js"><h3> - </h3></a>
                                                                </div>
                                                            </div>
                                                        </#if>
                                                    </#list>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                <!-- END SINGLE CHOISE -->
                                </div>

                                <div class="col-sm-12 d-flex flex-row align-items-center">
                                    <div class="col-sm-6 d-flex flex-row align-items-center">
                                        <div class="">
                                            <label>Obbligatoria: </label>
                                        </div>
                                        <div class="form-check form-check-inline pl-4">
                                            <label class="switch">
                                                <#if qst.mandatory>
                                                    <input name="isobbligo${qst.position}" type="checkbox" checked>
                                                <#else>
                                                    <input name="isobbligo${qst.position}" type="checkbox">
                                                </#if>
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
                    </#list>
                <#else>

                    <div id="card-header-js" class="price-box card col-md-8 offset-md-2 m-t-4" hidden>
                        <label for="questnumber"></label><input type="text" class="form-control question-input-js questnumber-js" name="numberquest1" hidden>
                        <div class="card-header border-bottom border-top border-left border-right" id="heading1">
                            <div class="row align-items-center align-text-center justify-content-between flex-nowrap px-5 collpase-header-js" data-toggle="collapse" data-target="#collapse1" aria-expanded="true" aria-controls="collapse1">
                                <h6 id="number-js" class="text-orange font-weight-bold mr-2">1</h6>
                                <h6 class="question-name-js">Nuova Domanda</h6>
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
                                    <input type="text" class="form-control info-js" name="info1" placeholder="Inserisci informazioni adizionali della domanda">
                                    <div class="invalid-feedback">
                                        Il campo non può essere vuoto
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-12 row pl-5 justify-content-between align-items-center py-2 flex-nowrap mb-4">
                                <div class="col-md-9 d-flex flex-row justify-content-center align-items-center">
                                    <label class="mr-3" for="exampleFormControlSelect1" style="white-space: nowrap">
                                        Seleziona Tipologia:
                                    </label>
                                    <select class="form-control" id="exampleFormControlSelect1" style="margin: 0!important">
                                        <option  value="answer" name="SINGLECHOISE" class="hidden-answer-js" selected>Scelta singola</option>
                                        <option  value="all" name="MULTIPLECHOISE" class="hidden-answer-js">Scelta multipla</option>
                                        <option  value="min-max" name="LONGTEXT" class="hidden-answer-js">Testo Lungo</option>
                                        <option  value="min-max" name="SHORTTEXT" class="hidden-answer-js">Testo Breve</option>
                                        <option  value="date" name="DATE" class="hidden-answer-js">Data</option>
                                        <option  value="min-max" name="NUMBER" class="hidden-answer-js">Numero</option>
                                    </select>
                                </div>
                            </div>

                            <div class="border-top pt-5 mx-5"></div>

                            <div id="answer-type-js">
                                <!-- SINGLE CHOISE -->
                                <div id="answer-field-js">
                                    <input class="question-type-js" name="questionType1" value="MULTIPLECHOISE" hidden>
                                    <div class="container-fluid">
                                        <div class="col-sm-12">
                                            <div id="append-answer-js" class="form-group">
                                                <div class="d-flex flex-row align-items-center justify-content-between question-option-js">
                                                    <div class="d-flex flex-column align-items-center justify-content-center flex-grow-1">
                                                        <input name="domanda1" type="search" class="form-control answer-js" placeholder="Inserisci domanda" required />
                                                    </div>
                                                    <div class="d-flex flex-column justify-content-center align-items-center align-text-center ml-3 delete_option_quest">
                                                        <a href="javascript:void(0)" class="text-orange font-weight-bold a-click remove-question2-js"><h3> - </h3></a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- END SINGLE CHOISE -->
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
                </#if>
            </div>
            <div class="d-flex flex-row justify-content-end align-items-center py-5 offset-md-2 m-t-4 col-md-8">
                <a id="another-question-js" class="btn btn-secondary rounded-pill btn-grey a-click"><b>Aggiungi Domanda</b></a>
            </div>
            <div class="d-flex flex-row justify-content-between align-items-center title-wrap mb-3 mt-4 offset-md-2 col-md-8">
                <div class="d-flex flex-column justify-content-center align-items-stretch py-5 m-t-4 col-md-6 nopaddingleft">
                    <input type="submit" value="Conferma modifica" class="btn btn-secondary rounded-pill btn-orange a-click" />
                </div>
                <div class="d-flex flex-column justify-content-center align-items-stretch py-5 m-t-4 col-md-6 nopaddingright">
                    <a class="btn btn-block btn-secondary btn-red changestatuspoll-js" href="javascript:void(0)">Concludi</a>
                </div>
            </div>
        </form>

        <!--- Second section - Change status ---->

        <div id="card-header-js-plh" class="price-box card col-md-8 offset-md-2 m-t-4" hidden>
            <input type="text" class="form-control question-input-js questnumber-js" name="numberquest1" hidden>
            <div class="card-header border-bottom border-top border-left border-right" id="heading">
                <div class="row align-items-center align-text-center justify-content-between flex-nowrap px-5 collpase-header-js" data-toggle="collapse" data-target="#collapse" aria-expanded="true" aria-controls="collapse">
                    <h6 id="number-js" class="text-orange font-weight-bold mr-2">1</h6>
                    <h6 class="question-name-js">Nuova Domanda</h6>
                    <a href="javascript:void(0)" class="text-orange font-weight-bold a-click remove-question-js ml-2"><h3> - </h3></a>
                </div>
            </div>
            <div class="card-body border-bottom border-left border-right collapse show card-body-js" aria-labelledby="heading" data-parent="#accordionExample" id="collapse">
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
                        <input type="text" class="form-control info-js" name="info1" placeholder="Inserisci informazioni adizionali della domanda">
                        <div class="invalid-feedback">
                            Il campo non può essere vuoto
                        </div>
                    </div>
                </div>

                <div class="col-md-12 row pl-5 justify-content-between align-items-center py-2 flex-nowrap mb-4">
                    <div class="col-md-9 d-flex flex-row justify-content-center align-items-center">
                        <label class="mr-3" for="exampleFormControlSelect1" style="white-space: nowrap">
                            Seleziona Tipologia:
                        </label>
                        <select class="form-control" id="exampleFormControlSelect1" style="margin: 0!important">
                            <option  value="answer" name="SINGLECHOISE" class="hidden-answer-js" selected>Scelta singola</option>
                            <option  value="all" name="MULTIPLECHOISE" class="hidden-answer-js">Scelta multipla</option>
                            <option  value="min-max" name="LONGTEXT" class="hidden-answer-js">Testo Lungo</option>
                            <option  value="min-max" name="SHORTTEXT" class="hidden-answer-js">Testo Breve</option>
                            <option  value="date" name="DATE" class="hidden-answer-js">Data</option>
                            <option  value="min-max" name="NUMBER" class="hidden-answer-js">Numero</option>
                        </select>
                    </div>
                </div>

                <div class="border-top pt-5 mx-5"></div>
                <div id="answer-type-js">
                    <!-- SINGLE CHOISE -->
                    <div id="answer-field-js">
                        <input class="question-type-js" name="questionType1" value="SINGLECHOISE" hidden>
                        <div class="container-fluid">
                            <div class="col-sm-12">
                                <div id="append-answer-js" class="form-group">
                                    <div class="d-flex flex-row align-items-center justify-content-between question-option-js">
                                        <div class="d-flex flex-column align-items-center justify-content-center flex-grow-1">
                                            <input name="domanda1" type="search" class="form-control answer-js" placeholder="Inserisci domanda" required />
                                        </div>
                                        <div class="d-flex flex-column justify-content-center align-items-center align-text-center ml-3 delete_option_quest">
                                            <a href="javascript:void(0)" class="text-orange font-weight-bold a-click remove-question2-js"><h3> - </h3></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- END SINGLE CHOISE -->
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

        <!-- SWITCH ANSWER TYPE -->

        <!-- SINGLE CHOISE -->
        <div id="answer-field-js-plh" hidden>
            <input class="question-type-js" name="questionType1" value="SINGLECHOISE" hidden>
            <div class="container-fluid">
                <div class="col-sm-12">
                    <div id="append-answer-js" class="form-group">
                        <div class="d-flex flex-row align-items-center justify-content-between question-option-js">
                            <div class="d-flex flex-column align-items-center justify-content-center flex-grow-1">
                                <input name="domanda1" type="search" class="form-control answer-js" placeholder="Inserisci domanda" required />
                            </div>
                            <div class="d-flex flex-column justify-content-center align-items-center align-text-center ml-3 delete_option_quest">
                                <a href="javascript:void(0)" class="text-orange font-weight-bold a-click remove-question2-js"><h3> - </h3></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- END SINGLE CHOISE -->
        <!-- MULTIPLE CHOISE -->
        <div id="all-field-js-plh" hidden="true">
            <input class="question-type-js" name="questionType1" value="MULTIPLECHOISE" hidden>
            <div class="col-md-12">
                <div class="d-flex flex-row align-items-center justify-content-center px-4 mt-2 mb-4">
                    <input name="min1" type="number" class="form-control mr-3 form-contro-maxmin min-js" placeholder="minimo risposte multiple *" required>
                    <input name="max1" type="number" class="form-control form-contro-maxmin max-js" placeholder="massimo risposte multiple *" required>
                </div>
            </div>
            <div class="container-fluid">
                <div class="col-sm-12">
                    <div id="append-answer-js" class="form-group">
                        <div class="d-flex flex-row align-items-center justify-content-between question-option-js">
                            <div class="d-flex flex-column align-items-center justify-content-center flex-grow-1">
                                <input name="domanda1" type="search" class="form-control answer-js" placeholder="Inserisci domanda"  required />
                            </div>
                            <div class="d-flex flex-column justify-content-center align-items-center align-text-center ml-3 delete_option_quest">
                                <a href="javascript:void(0)" class="text-orange font-weight-bold a-click remove-question2-js"><h3> - </h3></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- END MULTIPLE CHOISE-->
        <!-- NUMBER, SHORT AND LONG TEXT -->
        <div id="min-max-field-js-plh" hidden="true">
            <input class="question-type-js" name="questionType1" value="SHORTTEXT" hidden>
            <div class="col-md-12">
                <div class="d-flex flex-row align-items-center justify-content-center px-4 mt-2 mb-4">
                    <input name="min1" type="number" class="form-control mr-3 form-contro-maxmin min-js" placeholder="minimo risposte multiple *" required>
                    <input name="max1" type="number" class="form-control form-contro-maxmin max-js" placeholder="massimo risposte multiple *" required>
                </div>
            </div>
        </div>
        <!-- END NUMBER, SHORT AND LONG TEXT-->
        <!-- DATE -->
        <div id="date-field-js-plh" hidden="true">
            <input class="question-type-js" name="questionType1" value="DATE" hidden>
            <div class="col-md-12">
                <div class="d-flex flex-row align-items-center justify-content-center px-4 mt-2 mb-4">
                    <input name="min1" type="date" class="form-control mr-3 form-contro-maxmin min-js" placeholder="minimo risposte multiple *" required>
                    <input name="max1" type="date" class="form-control form-contro-maxmin max-js" placeholder="massimo risposte multiple *" required>
                </div>
            </div>
        </div>
        <!-- SWITCH ANSWER TYPE -->
    </div>
</section>

