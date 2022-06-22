
		GET: $(document).ready(
      function() {
         // GET REQUEST
         $("#getALlBooks").click(function(event) {
            event.preventDefault();
            ajaxGet();
         });

         // DO GET
         function ajaxGet() {
            $.ajax({
               type : "GET",
               url : "getBooks",
               success : function(result) {
                  if (result.status == "success") {
                     $('#getResultDiv ul').empty();
                     $.each(result.data, function (i, client) {
                                    var user =
                                        "customer Name  " +
                                        client.bookName +
                                        ", email  = " +
                                        client.author +
                                        "<br>";
                                    $("#getResultDiv .list-group").append(user);
                                });
                     console.log("Success: ", result);
                  } else {
                     $("#getResultDiv").html("<strong>Error</strong>");
                     console.log("Fail: ", result);
                  }
               },
               error : function(e) {
                  $("#getResultDiv").html("<strong>Error</strong>");
                  console.log("ERROR: ", e);
               }
            });
         }
      })