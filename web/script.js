const variants = {
  Pub: {
    Nerdy: {
      line: 'Dublin are like a well-written microservice - lots of moving parts, but somehow it all deployed on time.',
      why: 'High pressure, tight margins, and nobody wants to own the late mistake.',
      ask: 'Did you watch all of it, or just the final stretch?'
    },
    Safe: {
      line: 'Tough game. Great quality. Could have gone either way.',
      why: 'Two rivals, one-point swing, and lots of emotional investment.',
      ask: 'Who stood out most for you?'
    },
    Funny: {
      line: 'That finish had full on outage-war-room energy.',
      why: 'Everything was calm until everyone started shouting at once.',
      ask: 'Who gets blamed in the group chat today?'
    },
    Confident: {
      line: 'Dublin managed the final phase like a team that trusted its system.',
      why: 'Decision making under pressure usually decides games this close.',
      ask: 'Do you think the bench changed the game?'
    }
  },
  'Office Kitchen': {
    Nerdy: {
      line: 'Classic production pressure: small errors, big impact, final-minute fix.',
      why: 'Momentum shifts quickly when both teams are running hot.',
      ask: 'What was your read on the final ten minutes?'
    },
    Safe: {
      line: 'Great match to watch, very little between them.',
      why: 'Top teams, high quality execution, and one key moment.',
      ask: 'Was the result fair overall?'
    },
    Funny: {
      line: 'I only saw highlights, but it looked like chaos with uniforms.',
      why: 'Lots happened quickly and everyone thinks they predicted it.',
      ask: 'Any takes I should steal for stand-up later?'
    },
    Confident: {
      line: 'Game management won it, not raw talent.',
      why: 'Structure under pressure beats panic every time.',
      ask: 'Who controlled the tempo best?'
    }
  },
  Taxi: {
    Nerdy: {
      line: 'Think of it like latency: tiny delays, huge consequences.',
      why: 'One slow decision was enough to swing the finish.',
      ask: 'Did the momentum feel obvious live?'
    },
    Safe: {
      line: 'Brilliant contest. Could not call it until the end.',
      why: 'Both teams had phases where they looked in control.',
      ask: 'Will this change the final outlook?'
    },
    Funny: {
      line: 'I understood just enough to look informed for thirty seconds.',
      why: 'It had enough drama for people to keep talking all week.',
      ask: 'What is the least controversial opinion here?'
    },
    Confident: {
      line: 'They were more clinical in the key moments.',
      why: 'Close matches reward teams that execute repeatable patterns.',
      ask: 'What tactical tweak made the difference?'
    }
  },
  'Family Event': {
    Nerdy: {
      line: 'Solid architecture, noisy runtime, successful deployment.',
      why: 'The game looked chaotic but the patterns were clear.',
      ask: 'Was this better than last season\'s meetings?'
    },
    Safe: {
      line: 'Big rivalry game, great intensity, deserved winner.',
      why: 'People care because this fixture always has history behind it.',
      ask: 'Do you think they can back it up next week?'
    },
    Funny: {
      line: 'I came prepared with one opinion and one exit strategy.',
      why: 'Everyone has a hot take before the tea is poured.',
      ask: 'Who had the boldest prediction today?'
    },
    Confident: {
      line: 'They won the physical battle and controlled territory late on.',
      why: 'Late-game composure was the separator.',
      ask: 'Any player now locked in as starter?'
    }
  }
};

const scene = document.getElementById('scene');
const tone = document.getElementById('tone');
const result = document.getElementById('result');
const sheetWhy = document.getElementById('sheet-why');
const sheetQuestion = document.getElementById('sheet-question');
const sheetWhat = document.getElementById('sheet-what');
const sheetAvoid = document.getElementById('sheet-avoid');

function applyLine() {
  const selectedScene = scene.value;
  const selectedTone = tone.value;
  const data = variants[selectedScene][selectedTone];

  result.textContent = `"${data.line}"`;
  sheetWhat.textContent = 'Dublin beat Kerry by a point in a tense semi-final.';
  sheetWhy.textContent = data.why;
  sheetQuestion.textContent = `"${data.ask}"`;
  sheetAvoid.textContent = selectedTone === 'Funny'
    ? 'Do not overdo the joke if nobody else is laughing.'
    : 'Avoid claiming you watched the whole game if you did not.';
}

document.getElementById('generate').addEventListener('click', applyLine);
document.getElementById('cta-generate').addEventListener('click', () => {
  document.getElementById('generator').scrollIntoView({ behavior: 'smooth' });
  applyLine();
});
document.getElementById('explain').addEventListener('click', () => {
  sheetWhy.scrollIntoView({ behavior: 'smooth', block: 'center' });
});
document.getElementById('followup').addEventListener('click', () => {
  sheetQuestion.scrollIntoView({ behavior: 'smooth', block: 'center' });
});
document.getElementById('copy').addEventListener('click', async () => {
  try {
    await navigator.clipboard.writeText(result.textContent.replaceAll('"', ''));
  } catch {
    // Ignore clipboard failures on locked-down browsers.
  }
});

applyLine();
