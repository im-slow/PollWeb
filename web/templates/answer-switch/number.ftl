<div class="form-input">
    <#if question.mandatory>
        <input class="form-control" type="number" name="answer${question.position}" placeholder="Inserisci un valore numerico" required>
    <#else>
        <input class="form-control" type="number" name="answer${question.position}" placeholder="Inserisci un valore numerico">
    </#if>
</div>