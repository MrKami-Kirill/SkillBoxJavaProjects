$(function(){
    const appendTask = function(data) {
        if (data.status == 'CREATED' && new Date() < new Date(data.date)) {
            var task = '<b>' + data.date + ' ' + data.status + ':</b> ' + data.name +
            ' <a href="#" class="in-progress-link">In Progress</a> <a href="#" class="canceled-link">Cancel</a><br>'
            + 'Task description: ' + data.description;
            $('#task-list-created').append('<div id="' + data.id + '">' + task + "</div>");
        }
        if (data.status == 'IN_PROGRESS' && new Date() < new Date(data.date)) {
            var task = '<b>' + data.date + ' ' + data.status + ':</b> ' + data.name +
            ' <a href="#" class="completed-link">Complete</a>  <a href="#" class="canceled-link">Cancel</a><br>'
            + 'Task description: ' + data.description;
            $('#task-list-in-progress').append('<div id="' + data.id + '">' + task + "</div>");
        }
        if (data.status == 'CANCELED') {
            var task = '<b>' + data.date + ' ' + data.status + ':</b> ' + data.name +
            ' <a href="#" class="in-progress-link">In Progress</a>  <a href="#" class="deleted-link">Delete</a><br>'
            + 'Task description: ' + data.description;
            $('#task-list-canceled').append('<div id="' + data.id + '">' + task + "</div>");
        }
        if (data.status == 'CLOSED') {
            var task = '<b>' + data.date + ' ' + data.status + ':</b> ' + data.name +
            ' <a href="#" class="deleted-link">Delete</a><br>'
            + 'Task description: ' + data.description;
            $('#task-list-closed').append('<div id="' + data.id + '">' + task + "</div>");
        }
        if (data.status == 'COMPLETED') {
            var task = '<b>' + data.date + ' ' + data.status + ':</b> ' + data.name +
            ' <a href="#" class="closed-link">Close</a><br>'
            + 'Task description: ' + data.description;
            $('#task-list-completed').append('<div id="' + data.id + '">' + task + "</div>");
        }
        if (data.status == 'DELETED') {
            var task = '<b>' + data.date + ' ' + data.status + ':</b> ' + data.name +
            ' <a href="#" class="del-link">Out of history (forever)!!!</a><br>'
            + 'Task description: ' + data.description;
            $('#task-list-deleted').append('<div id="' + data.id + '">' + task + "</div>");
        }
        if (data.status == 'EXPIRED') {
            var task = '<b>' + data.date + ' ' + data.status + ':</b> ' + data.name +
            ' <a href="#" class="del-link">Out of history (forever)!!!</a><br>'
            + 'Task description: ' + data.description;
            $('#task-list-expired').append('<div id="' + data.id + '">' + task + "</div>");
        }
        if (new Date() > new Date(data.date)) {
            if ((data.status == 'CREATED') || (data.status == 'IN_PROGRESS')) {
                var task = '<b>' + data.date + ' EXPIRED ' + ':</b> ' + data.name +
                ' <a href="#" class="del-link">Out of history (forever)!!!</a><br>'
                + 'Task description: ' + data.description;
                $('#task-list-expired').append('<div id="' + data.id + '">' + task + "</div>");
            }
        }
    };

    // get taskList
    $.get('/list/', function(response){
        for (i in response) {
            appendTask(response[i]);
        }
    });

    //create task
    $('#create-task').click(function(){
        var data = $('#list-form form').serialize();
        var url = null;
        if (new Date() > new Date(data.date)) {
            data.status = 'EXPIRED';
            url = '/listExpired/'
        } else {
            data.status = 'CREATED';
            url = '/list/'
        }
        $.ajax({
            method: "POST",
            url: url,
            data: data
        });
        window.location.reload();
    });

    //in progress task
    $(document).on('click', '.in-progress-link', function(){
        var id = $(this).parent().attr("id");
        var status = 'IN_PROGRESS';
        $.ajax({
            method: "POST",
            url: "/listInProgress/" + id,
            data: status
        });
        window.location.reload();
    });

    //canceled task
    $(document).on('click', '.canceled-link', function(){
        var id = $(this).parent().attr("id");
        var status = 'CANCELED';
        $.ajax({
            method: "POST",
            url: "/listCanceled/" + id,
            data: status
        });
        window.location.reload();
    });

    //closed task
    $(document).on('click', '.closed-link', function(){
        var id = $(this).parent().attr("id");
        var status = 'CLOSED';
        $.ajax({
            method: "POST",
            url: "/listClosed/" + id,
            data: status
        });
        window.location.reload();
    });

    //completed task
    $(document).on('click', '.completed-link', function(){
        var id = $(this).parent().attr("id");
        var status = 'COMPLETED';
        $.ajax({
            method: "POST",
            url: "/listCompleted/" + id,
            data: status
        });
        window.location.reload();
    });

    //deleted task
    $(document).on('click', '.deleted-link', function(){
        var id = $(this).parent().attr("id");
        var status = 'DELETED';
        $.ajax({
            method: "POST",
            url: "/listDeleted/" + id,
            data: status
        });
        window.location.reload();
    });

    //delete forever task
    $(document).on('click', '.del-link',function(){
        var id = $(this).parent().attr("id");
        $.ajax({
            method: "POST",
            url: "/list/" + id
        });
        window.location.reload();
    });
});
