<div class="form-input">
    <#if question.mandatory>
        <input id="datepicker" class="form-control" placeholder="Inserisci una data nel seguente formato dd/mm/yyyy" name="answer${question.position}" required/>
    <#else>
        <input id="datepicker" class="form-control" placeholder="Inserisci una data nel seguente formato dd/mm/yyyy" name="answer${question.position}" />
    </#if>
</div>