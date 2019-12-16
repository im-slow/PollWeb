<div class="form-input">
    <#if question.mandatory>
        <input class="form-control" type="number" name="number-text-content" placeholder="Inserisci un valore numerico" required>
    <#else>
        <input class="form-control" type="number" name="number-text-content" placeholder="Inserisci un valore numerico">
    </#if>
</div>