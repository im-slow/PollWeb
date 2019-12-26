<!-- Contact Form Section -->
<section id="gtco-contact-form" class="bg-white">
    <div class="section-content">
        <!-- Section Title -->
        <div class="col justify-content-center align-items-center title-wrap mb-3">
            <h2 class="section-title">${poll.title}</h2>
            <p class="section-sub-title">${poll.opentext}</p>
        </div>
        <!-- End of Section Title -->
        <input id="pollID-js" type="text" name="idpoll" value="${poll.ID}" hidden>
        <#list question as qst>
            <div class="price-box card col-md-8 offset-md-2 m-t-4 mb-4">
                <div class="card-header border-bottom border-top border-left border-right">
                    <div class="row align-items-center align-text-center flex-nowrap px-5">
                        <h6 class="text-orange font-weight-bold mr-2"> ${qst.position} </h6>
                        <h6 style="flex-grow: 1; text-align: center;">${qst.questionText}</h6>
                    </div>
                </div>
                <div class="card-body border-bottom border-left border-right">
                    <div class="col-md-12">
                        <label class="pl-4">Inserici la domanda</label>
                        <div class="col-md-12 form-input">
                            <input type="text" class="form-control" placeholder="Inserisci qui la domanda" value="${qst.questionText}" readonly>
                        </div>
                    </div>
                    <div class="col-md-12">
                        <label class="pl-4">Note della domanda</label>
                        <div class="col-md-12 form-input">
                            <input type="text" class="form-control" placeholder="Nota della domanda" value="${qst.note}" readonly>
                        </div>
                    </div>

                    <div class="pt-5 mx-5"></div>

                        <!-- SINGLE CHOISE -->
                            <div class="container-fluid">
                                <div class="col-sm-12">
                                    <div class="form-group">
                                        <#list qst.QAnswer as answer>
                                            <#if answer != "">
                                                <div class="d-flex flex-row align-items-center">
                                                    <div class="d-flex flex-column align-items-center justify-content-center flex-grow-1">
                                                        <input class="form-control" placeholder="Inserisci domanda" value="${answer}" readonly />
                                                    </div>
                                                </div>
                                            </#if>
                                        </#list>
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
                                        <input type="checkbox" onclick="return false;" checked>
                                    <#else>
                                        <input type="checkbox" onclick="return false;">
                                    </#if>
                                    <span class="slider round"></span>
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </#list>
        <#if poll.openstatus>
            <div class="d-flex flex-row justify-content-between align-items-center title-wrap mb-3 mt-5 offset-md-2 col-md-8">
                <div class="d-flex flex-column justify-content-center align-items-stretch py-5 m-t-5 col-md-12 nopadding">
                    <a class="btn btn-block btn-secondary btn-red" href="pubblicasondaggio?id=${poll.ID}">Chiudi Sondaggio</a>
                </div>
            </div>
        <#else>
        <div class="d-flex flex-row justify-content-between align-items-center title-wrap mb-3 mt-5 offset-md-2 col-md-8">
            <h4>Aggiungi utenti al sondaggio privato</h4><a href="javascript:void(0)" class="text-orange font-weight-bold a-click new-answer-js pr-2"><h3> + </h3></a>
        </div>
        <div id="answer-field-js">
            <input class="question-type-js" name="questionType1" value="SINGLECHOISE" hidden>
            <div class="container-fluid col-sm-8">
                <div class="align-items-center justify-content-center col-sm-12 nopadding">
                    <div id="append-answer-js" class="form-group">
                        <div class="d-flex flex-row align-items-center justify-content-between">
                            <div class="d-flex flex-column align-items-center justify-content-center flex-grow-1 question-option-js">
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
        <div class="d-flex flex-row justify-content-between align-items-center title-wrap mb-3 mt-4 offset-md-2 col-md-8">
            <div class="d-flex flex-column justify-content-center align-items-stretch py-5 m-t-4 col-md-6 nopaddingleft">
                <input type="submit" value="Aggiungi utente" class="btn btn-secondary rounded-pill btn-orange a-click" />
            </div>
            <div class="d-flex flex-column justify-content-center align-items-stretch py-5 m-t-4 col-md-6 nopaddingright">
                <a class="btn btn-block btn-secondary btn-red" href="pubblicasondaggio?id=${poll.ID}">Chiudi Sondaggio</a>
            </div>
        </div>
        </#if>
        <!--- Second section - Change status ---->
        </div>
</section>

