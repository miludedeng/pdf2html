 $(document).ready(function() {
        $("#file-upload").fileinput({
            'showPreview' : false,
            'allowedFileExtensions' : ['pdf'],
            'elErrorContainer': '#errorBlock'
        });
        $(".upload-btn").click(function(){
            uploadFile();
            return false;
        });
   });

   function uploadFile() {
      $.ajax({
        url: "/upload",
        type: "POST",
        data: new FormData($("#upload-file-form")[0]),
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        cache: false,
        success: function () {
            dealProcess();
            return false;
        },
        error: function () {
            alert("上传失败");
            return false;
        }
      });
    }

    function dealProcess(){
        var timer = setInterval(function(){
            $.ajax({
                url: "/process",
                cache: false,
                success: function(response){
                    if(response){
                        var lines = response.split("\r\n");
                        for(var i=0; i < lines.length; i++){
                            line = lines[i];
                            if(line.startsWith("Preprocessing")){
                                $("#progess_results").html("<p>"+line+"</p>");
                            }
                            if(line.startsWith("Working")){
                                $("#working_results").html("<p>"+line+"</p>");
                            }
                            if(!timer){
                                clearInterval(timer);
                            }
                        }
                    }

                }
            });
        },2000);
    }