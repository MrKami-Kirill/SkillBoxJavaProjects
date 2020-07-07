$(function(){
    //create task
    $('#create-task').click(function(){
        var data = $('#list-form form').serialize();
        var url = null;
        if (new Date() > new Date(data.date)) {
            url = '/listExpired/'
        } else {
            url = '/list/'
        }
        $.ajax({
            method: "POST",
            url: url,
            data: data
        });
        window.location.reload(true);
    });

        //delete task from database
        $(document).on('click', '.del-link',function(){
            var id = $(this).parent().attr("id");
            var xhr = new XMLHttpRequest();
            xhr.open('DELETE', 'http://localhost:8080/list/' + id, true);
            xhr.onreadystatechange = () => {
                    if (xhr.readyState === 4) {
                        this.parentElement.parentElement.removeChild(this.parentElement);
                    }
                  }
            xhr.send();
            window.location.reload(true);
        });

    //in progress task
    $(document).on('click', '.in-progress-link',function(){
            var id = $(this).parent().attr("id");
            $.ajax({
                method: "POST",
                url: "/listInProgress/" + id
            });
            window.location.reload(true);
        });

    //canceled task
    $(document).on('click', '.canceled-link', function(){
        var id = $(this).parent().attr("id");
        $.ajax({
            method: "POST",
            url: "/listCanceled/" + id
        });
        window.location.reload(true);
    });

    //closed task
    $(document).on('click', '.closed-link', function(){
        var id = $(this).parent().attr("id");
        $.ajax({
            method: "POST",
            url: "/listClosed/" + id
        });
        window.location.reload(true);
    });

    //completed task
    $(document).on('click', '.completed-link', function(){
        var id = $(this).parent().attr("id");
        $.ajax({
            method: "POST",
            url: "/listCompleted/" + id
        });
        window.location.reload(true);
    });

    //deleted task
    $(document).on('click', '.deleted-link', function(){
        var id = $(this).parent().attr("id");
        $.ajax({
            method: "POST",
            url: "/listDeleted/" + id
        });
        window.location.reload(true);
    });

    function windowReload() {
        window.location.reload(true);
        }
});
