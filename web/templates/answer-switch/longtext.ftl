<div class="d-flex flex-row justify-content-around align-items-center align-text-center">
    <p><b class="mr-2"># minimo caratteri: </b>${question.minimum}</p>
    <p><b class="mr-2"># massimo caratteri: </b>${question.maximum}</p>
</div>
<div class="form-check ml-3 d-flex flex-column justify-content-center align-items-start">
    <div class="form-input col-md-12 mb-3">
         <#if question.mandatory>
            <textarea type="text" class="form-control noresize" name="answer${question.position}" placeholder="Scrivi qui la tua risposta" rows="6" minlength="${question.minimum}" maxlength="${question.maximum}" required></textarea>
         <#else>
             <textarea type="text" class="form-control noresize" name="answer${question.position}" placeholder="Scrivi qui la tua risposta" rows="6" minlength="${question.minimum}" maxlength="${question.maximum}"></textarea>
         </#if>
    </div>
 </div>