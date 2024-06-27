window.onload = () => {
    const studyContainerEle = document.getElementById("studyContainer");
}

function deleteCheckedStudiesToPatientBookmark() {
    const checkedCheckboxes = $(".study-checkbox:checked");

    const checkedCheckboxIds = [];

    checkedCheckboxes.each(function() {
        checkedCheckboxIds.push(this.id);
    })

    console.log(checkedCheckboxIds)

    const data = { pids: checkedCheckboxIds };

    $.ajax({
        url: "/patientBookmark/delete",
        type: "DELETE",
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function(data) {
        },
        error: function(error) {
            console.error('Error fetching data', error);
        }
    });
}

function addCheckedStudiesToPatientBookmark() {
    const checkboxChecked = $(".study-checkbox:checkbox:checked");

    console.log(checkboxChecked);
}