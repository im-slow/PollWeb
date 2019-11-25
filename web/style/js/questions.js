//Add new Question
$(document).on('click', '#another-question-js', function () {
    const clone = $('#card-header-js').clone();
    $('#new-question-js').append(clone);
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
        Swal.fire({
            title: 'Sei sicuro di voler rimuovere la domanda?',
            text: 'Non sarai in grado di recuperarla successivamente!',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Elimina',
            cancelButtonText: 'Annulla'
        }).then((result) => {
            if (result.value) {
                $(this).parent().parent().parent().remove();
            } else if (result.dismiss === Swal.DismissReason.cancel) {
                console.log('Dismissed');
            }
        });
    }
    listChild();
});

function listChild() {
    let counter = 0;
    $('#new-question-js > div').each(function () {
        $(this).find('#number-js').html(++counter);
    });
}