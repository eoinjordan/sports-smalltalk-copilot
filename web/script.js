const lines = [
  '“Dublin are like a well-written microservice — lots of moving parts, but somehow it all deployed on time.”',
  '“That match had serious incident-response energy. Everyone had a theory, nobody had the logs.”',
  '“I only saw the highlights, but it sounds like classic production pressure: calm until the final deploy.”',
  '“Safe take: great game, high pressure, probably best not to blame the ref in mixed company.”'
];
let index = 0;
function generateLine() {
  index = (index + 1) % lines.length;
  document.getElementById('result').textContent = lines[index];
}
