<div class="d-flex flex-row justify-content-around align-items-center align-text-center">
        <p><b class="mr-2">data minima: </b>${question.minimum}</p>
        <p><b class="mr-2">data massima: </b>${question.maximum}</p>
</div>
<div class="form-input">
    <#if question.mandatory>
        <input id="datepicker" pattern="\d{1,2}/\d{1,2}/\d{4}" class="form-control" placeholder="Inserisci una data nel seguente formato dd/mm/yyyy" name="answer${question.position}" required/>
    <#else>
        <input id="datepicker" pattern="\d{1,2}/\d{1,2}/\d{4}" class="form-control" placeholder="Inserisci una data nel seguente formato dd/mm/yyyy" name="answer${question.position}" />
    </#if>
</div>