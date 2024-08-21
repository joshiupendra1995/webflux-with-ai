$(document).ready(function() {
            $('#submitButton').click(function(event) {
                event.preventDefault();
                var question = $('#question').val();
                console.log("Question submitted: " + question);

                // Show loader
                $('.loader').show();

                $.ajax({
                    url: '/v1/chat/askQuestion',
                    type: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify({question: question}),
                    success: function(response) {
                        console.log("Response received: ", response);
                        // Hide loader
                        $('.loader').hide();
                        // Ensure spaces between words in response
                        var formattedResponse = response.replace(/\s+/g, ' ');
                        $('#response').val(function(index, currentValue) {
                            if (currentValue === '') {
                                return formattedResponse; // No newline for the first response
                            } else {
                                return currentValue + '\n' + formattedResponse; // Append with newline for subsequent responses
                            }
                        });
                    },
                    error: function(jqXHR, textStatus, errorThrown) {
                        console.error("Error: " + textStatus + ", " + errorThrown);
                        console.error("Response: ", jqXHR.responseText);
                        // Hide loader on error
                        $('.loader').hide();
                    }
                });
            });

            $('#clearButton').click(function(event) {
                event.preventDefault();
                $('#question').val('');
                $('#response').val('');
                $('#question').focus(); // Set focus back to the question textarea
            });
        });