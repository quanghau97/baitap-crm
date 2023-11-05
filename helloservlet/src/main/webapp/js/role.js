$(document).ready(function() {
	// logic code


	$(".btn-xoa").click(function() {
		var id = $(this).attr("id-role")
		var This = $(this)
		// GỌI VÀO ĐƯỜNG DẪN VÀ LẤY DỮ LIỆU ĐÓ TRẢ RA
		$.ajax({
			method: "DELETE",
			url: "http://localhost:8080/helloservlet/api/role?id=" + id,
		//	data: { name: "John", location: "Boston" }  // CHỈ DÀNH CHO PHƯƠNG THỨC POST
		})
			.done(function(result) {
				if(result.data == true){
					This.closest("tr").remove()
				}
				console.log(result)
			});
	})
})

$(document).ready(function () {
$(".btn-update").click(function () {
        var id = $(this).attr("roleId")
        var name = $("#name").val()
        var description = $("#description").val()

        if(name == "" || description == "") {
            alert("Vui lòng nhập đầy đủ thông tin")
            return;
        }
})
      $.ajax({
            method: "post",
            url: "http://localhost:8080/crm/api/role/update",
            data: {id: id, name: name, desc: description }
        }).done(function( data ) {
            if(data.success){
                alert("Cập nhật thành công")
            }else{
                alert("Cập nhật thất bại")
            }
            window.location.href = 'http://localhost:8080/crm/role'
        });
    

    $(".btn-return").click(function () {
        window.location.href = 'http://localhost:8080/crm/role'
    })
})

