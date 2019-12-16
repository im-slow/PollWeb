<div class="form-input">
    <#if question.mandatory>
        <input class="form-control" type="text" name="small-text-content" placeholder="Scrivi qui la tua risposta" required>
    <#else>
        <input class="form-control" type="text" name="small-text-content" placeholder="Scrivi qui la tua risposta">
    </#if>
</div>