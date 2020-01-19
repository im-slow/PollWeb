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
                            <#if (question?size > 0)>
                            <#list question as q>
                                <#if (answer?size > 0)>
                                    <#list answer as a>
                                        <#if (true)>
                                        <tr>
                                            <th>
                                                ${q.position}
                                            </th>
                                            <td>
                                                ${q.questionText}
                                            </td>
                                            <td>
                                                ${a.answer}
                                            </td>
                                        </tr>
                                        </#if>
                                    </#list>
                                <#else>
                            <tr>
                                <th>
                                    ${q.position}
                                </th>
                                <td>
                                    ${q.questionText}
                                </td>
                                <td>
                                    Non sono presenti risposte
                                </td>
                                </#if>
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
