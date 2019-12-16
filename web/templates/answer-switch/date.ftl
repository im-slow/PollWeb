<div class="form-input">
    <#if question.mandatory>
        <input id="datepicker" class="form-control" name="date-text-content" placeholder="Inserisci una data nel seguente formato dd/mm/yyyy" required/>
    <#else>
        <input id="datepicker" class="form-control" name="date-text-content" placeholder="Inserisci una data nel seguente formato dd/mm/yyyy" />
    </#if>
</div>