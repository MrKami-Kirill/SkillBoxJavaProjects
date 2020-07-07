//delete forever task
$(function(){
    const appendBook = function(data){
        var userCode = '<a href="#" class="user-link" data-id="' +
            data.id + '">' + data.username + '</a><br>';
        $('#user-list')
            .append('<div>' + userCode + '</div>');
    };

    //Getting userInfo
    $(document).on('click', '.user-link', function(){
        var link = $(this);
        var userId = link.data('id');
        $.ajax({
            method: "GET",
            url: '/admin/' + userId,
            success: function(response)
            {
                var code =
                '<table border="inset">' +
                '<tr>' +
                '<th><strong>ID</strong></th>' +
                '<th><strong>Username</strong></th>' +
                '<th><strong>Password</strong></th>' +
                '<th><strong>First name</strong></th>' +
                '<th><strong>Last name</strong></th>' +
                '<th><strong>Middle Name</strong></th>' +
                '<th><strong>Birth date</strong></th>' +
                '<th><strong>E-mail</strong></th>' +
                '<th><strong>Admin code</strong></th>' +
                '</tr>' +
                '<tr>' +
                '<td><i>' + response.id + '<i></td>' +
                '<td><i>' + response.username + '<i></td>' +
                '<td><i>' + response.password + '<i></td>' +
                '<td><i>' + response.firstname + '<i></td>' +
                '<td><i>' + response.lastname + '<i></td>' +
                '<td><i>' + response.middlename + '<i></td>' +
                '<td><i>' + response.birthdate + '<i></td>' +
                '<td><i>' + response.email + '<i></td>' +
                '<td><i>' + response.adminCode + '<i></td>' +
                '</tr>' +
                '</table>';
                link.parent().append(code);
            },
            error: function(response)
            {
                if(response.status == 404) {
                    alert('User not found!');
                }
            }
        });
        return false;
    });

    $('click', '.user-link').click(function(event){
            if(event.target === this) {
                $(this).css('display', 'none');
            }
        });

    ('#delete-user').click(function(){
        var id = $(this).parent().attr("id");
            $.ajax({
                 method: "POST",
                 url: "/admin/" + id
            });
            window.location.reload();
        });



});