var deleta_aluno = function(id){
	$.ajax({
	    url: '/v1/aluno/' + id,
	    type: 'DELETE',
	    success: function(result) {
	    	location.reload();
	    }
	});
}