<div class="d-flex flex-row justify-content-around align-items-center align-text-center">
        <p><b class="mr-2">data minima: </b>${question.minimum}</p>
        <p><b class="mr-2">data massima: </b>${question.maximum}</p>
</div>
<div class="form-input">
    <#if question.mandatory>
        <input id="datepicker" pattern="(0[1-9]|1[0-9]|2[0-9]|3[01]).(0[1-9]|1[012]).[0-9]{4}" class="form-control" placeholder="Inserisci una data nel seguente formato dd/mm/yyyy" name="answer${question.position}" required/>
    <#else>
        <input id="datepicker" pattern="(0[1-9]|1[0-9]|2[0-9]|3[01]).(0[1-9]|1[012]).[0-9]{4}" class="form-control" placeholder="Inserisci una data nel seguente formato dd/mm/yyyy" name="answer${question.position}" />
    </#if>
</div>