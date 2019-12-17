<div class="form-check ml-3 d-flex flex-column justify-content-center align-items-start">
    <div class="form-input col-md-12 mb-3">
         <#if question.mandatory>
            <textarea type="text" class="form-control noresize" name="big-text-content" placeholder="Scrivi qui la tua risposta" rows="6" minlength="${question.minimum}" maxlength="${question.maximum}" required></textarea>
         <#else>
             <textarea type="text" class="form-control noresize" name="big-text-content" placeholder="Scrivi qui la tua risposta" rows="6" minlength="${question.minimum}" maxlength="${question.maximum}"></textarea>
         </#if>
    </div>
 </div>