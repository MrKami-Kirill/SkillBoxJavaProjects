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
        var obj = document.getElementById(userId);
//        if (obj.style.display != "block") {
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
//                '<th><strong>Password</strong></th>' +
                '<th><strong>Full name</strong></th>' +
//                '<th><strong>Last name</strong></th>' +
//                '<th><strong>First name</strong></th>' +
//                '<th><strong>Middle name</strong></th>' +
//                '<th><strong>Gender</strong></th>' +
//                '<th><strong>Birth date</strong></th>' +
//                '<th><strong>E-mail</strong></th>' +
//                '<th><strong>Admin code</strong></th>' +
                '</tr>' +
                '<tr>' +
                '<td><i>' + response.id + '<i></td>' +
                '<td><i>' + response.username + '<i></td>' +
//                '<td><i>' + response.password + '<i></td>' +
                '<td><i>' + response.fullname + '<i></td>' +
//                '<td><i>' + response.surname + '<i></td>' +
//                '<td><i>' + response.name + '<i></td>' +
//                '<td><i>' + response.patronymic + '<i></td>' +
//                '<td><i>' + response.gender + '<i></td>' +
//                '<td><i>' + response.birthdate + '<i></td>' +
//                '<td><i>' + response.email + '<i></td>' +
//                '<td><i>' + response.adminCode + '<i></td>' +
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
//        obj.style.display = "block";
//        return false;
//        }
//        else {
//            obj.style.display = "none";
//        }
//        window.location.reload(true);
    });


    //delete user from database
    $(document).on('click', '.del-link', function(){
        var id = $(this).parent().attr("id");
        var xhr = new XMLHttpRequest();
        xhr.open('DELETE', 'http://localhost:8080/admin/' + id, true);
        xhr.onreadystatechange = () => {
            if (xhr.readyState === 4) {
                this.parentElement.parentElement.removeChild(this.parentElement);
                }
            }
        xhr.send();
        window.location.reload(true);
    });

    function windowReload() {
    window.location.reload(true);
    }
});