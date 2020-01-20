<section id="gtco-blog" class="bg-grey">
    <div class="container">
        <div class="section-content">
            <div class="title-wrap mb-5">
                <h2 class="section-title">Risposte del sondaggio</h2>
                <p class="section-sub-title">${poll.title}</p>
            </div>
            <div class="row">
                <!-- Blog -->
                <div class="col-md-12 blog-holder">
                    <div class="row">
                        <table class="table">
                            <tr>
                                <th>
                                    #
                                </th>
                                <th>
                                    Domanda
                                </th>
                                <th>
                                    Risposta
                                </th>
                            </tr>
                            <#if (answer?size > 0)>
                                <#list answer as a>
                                    <tr>
                                        <th>
                                            ${a.question.position}
                                        </th>
                                        <td>
                                            ${a.question.questionText}
                                        </td>
                                        <td>
                                            ${a.answer}
                                        </td>
                                    </tr>
                                </#list>
                            <#else>
                                <h3>Non sono presenti domande</h3>
                            </#if>
                        </table>
                    </div>
                </div>
            <!-- End of Blog -->
            </div>
        </div>
    </div>
</section>
