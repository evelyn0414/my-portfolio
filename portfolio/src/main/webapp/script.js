
/**
 * Adds a random fact about me to the page.
 */
function createMap(){
    const map = new google.maps.Map(
        document.getElementById('map'),
        {center: {lat: 31.30227, lng: 120.63132}, zoom: 7});
    new google.maps.Marker(
          {position: {lat: 31.30227, lng: 120.63132},
           map: map,
           title: 'Suzhou'});
    
}

function addRandomFact() {
  const facts =
      ['She loves icecreams🍦', 'She lives in Suzhou.', 'She loves photography.', 'She loves cooking and baking!'];

  // Pick a random one.
  const fact = facts[Math.floor(Math.random() * facts.length)];

  // Add it to the page.
  const factContainer = document.getElementById('fact-container');
  factContainer.innerText = fact;
  console.log("done!");
}

/** Fetches comments from the server and adds them to the DOM. */
function loadComments() {
  fetch('/list-comments').then(response => response.json()).then((comments) => {
    const commentListElement = document.getElementById('comment-list');
    comments.forEach((comment) => {
      commentListElement.appendChild(createCommentElement(comment));
    })
  });
}

/** Creates an element that represents a comment, including its delete button. */
function createCommentElement(comment) {
  const commentElement = document.createElement('tr');
  
  const nameElement = document.createElement('td');
  nameElement.innerText = comment.name;

//   const emailElement = document.createElement('td');
//   emailElement.innerText = comment.email;

  const messageElement = document.createElement('td');
  messageElement.innerText = comment.message;

  const dateElement = document.createElement('td');
  const date = new Date(comment.timestamp);
  dateElement.innerText = date.toDateString();
  

  commentElement.appendChild(nameElement);
//   commentElement.appendChild(emailElement);
  commentElement.appendChild(messageElement);
  commentElement.appendChild(dateElement);
  
  return commentElement;
}


// // fetch message from the server
// async function getRandomFact() {
//   const response = await fetch('/random-fact');
//   const textResponse = await response.text();
//   const strings = textResponse.split('\n');
//   const fact = strings[0];
//   const image = strings[1];
//   var container = document.getElementById('fact-container');
//   var myImage = document.createElement("img");
//   myImage.src = image;
//   container.innerText = fact + "\n";
//   container.appendChild(myImage);
//   myImage.style.maxWidth = '60%';

// }

// fetch message from the server in json format
function getRandomFact() {
  fetch('/random-fact').then(response => response.json()).then((arr) => {
    // stats is an object, not a string, so we have to
    // reference its fields to create HTML content
    const fact = arr[0];
    const image = arr[1];
    var container = document.getElementById('fact-container');
    var myImage = document.createElement("img");
    myImage.src = image;
    container.innerText = fact + "\n";
    container.appendChild(myImage);
    myImage.style.maxWidth = '60%';
  });
}

(function($) {

	var	$window = $(window),
		$body = $('body'),
		$wrapper = $('#wrapper'),
		$header = $('#header'),
		$nav = $('#nav'),
		$main = $('#main'),
		$navPanelToggle, $navPanel, $navPanelInner;

	// Breakpoints.
		breakpoints({
			default:   ['1681px',   null       ],
			xlarge:    ['1281px',   '1680px'   ],
			large:     ['981px',    '1280px'   ],
			medium:    ['737px',    '980px'    ],
			small:     ['481px',    '736px'    ],
			xsmall:    ['361px',    '480px'    ],
			xxsmall:   [null,       '360px'    ]
		});

	// Intro.
		var $intro = $('#intro');

		if ($intro.length > 0) {


			// Hide intro on scroll (> small).
				breakpoints.on('>small', function() {

					$main.unscrollex();

					$main.scrollex({
						mode: 'bottom',
						top: '25vh',
						bottom: '-50vh',
						enter: function() {
							$intro.addClass('hidden');
							$header.removeClass('hidden');
						},
						leave: function() {
							$intro.removeClass('hidden');
							$header.addClass('hidden');
						}
					});

				});

			// Hide intro on scroll (<= small).
				breakpoints.on('<=small', function() {

					$main.unscrollex();

					$main.scrollex({
						mode: 'middle',
						top: '15vh',
						bottom: '-15vh',
						enter: function() {
							$intro.addClass('hidden');
							$header.removeClass('hidden');
						},
						leave: function() {
							$intro.removeClass('hidden');
							$header.addClass('hidden');
						}
					});

			});

    }
    return $(this);

})(jQuery);