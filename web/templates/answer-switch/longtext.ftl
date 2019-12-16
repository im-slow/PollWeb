 <div class="form-input">
     <#if question.mandatory>
        <textarea type="text" class="form-control noresize" name="big-text-content" placeholder="Scrivi qui la tua risposta" rows="6" minlength="${question.minimum}" maxlength="${question.maximum}" required></textarea>
     <#else>
         <textarea type="text" class="form-control noresize" name="big-text-content" placeholder="Scrivi qui la tua risposta" rows="6" minlength="${question.minimum}" maxlength="${question.maximum}"></textarea>
     </#if>
 </div>