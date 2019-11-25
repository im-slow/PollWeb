//Make clone of the first div
var basicAnswer;

$(document).ready(function () {
    basicAnswer = $('#card-header-js').clone();
});

//Add new Question
$(document).on('click', '#another-question-js', () => {
    $('#new-question-js').append(basicAnswer.clone()).hide().fadeIn(1000);
    listChild();
});

//Toogle Effect
$(document).on('click', '.card-header', function (e) {
    $(this).parent().find('.card-body').slideToggle();
});

//Remove Questions
$(document).on('click', '.remove-question-js', function (e) {
    e.stopPropagation();
    let element = 0;
    $('#new-question-js > div').each(function () {
        element++;
    });
    if (element > 1) {
        swal({
            title: 'Sei sicuro di voler rimuovere la domanda?',
            text: 'Non sarai in grado di recuperarla successivamente!',
            icon: 'warning',
            buttons: ['Annulla', 'Elimina']
        }).then((willDelete) => {
            if (willDelete) {
                $(this).parent().parent().parent().remove();
                listChild();
            } else {
                console.log('Dismissed');
            }
        });
    }
});

//Keep the answer name on focus
$(document).on('change', '.question-input-js', function () {
    $(this).parent().parent().parent().parent().find('.question-name-js').html($(this).val());
});

function listChild() {
    let counter = 0;
    $('#new-question-js > div').each(function () {
        $(this).find('#number-js').html(++counter);
        var namequestion = $(this).find('.question-input-js').val();
        if (namequestion !== '') {
            $(this).find('.question-name-js').html(namequestion);
        }
    });
}