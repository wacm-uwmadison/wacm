document.addEventListener("DOMContentLoaded", () => {

    const tableBody = document.querySelector("#past-events tbody");
  
    past_events_list.forEach(event => {
      const row = document.createElement("tr");
  
      const dateCell = document.createElement("td");
      dateCell.className = "date";
      dateCell.innerHTML = `
        ${event.date.month} <h4>${event.date.day}</h4> <p>${event.date.time}</p>
      `;
      row.appendChild(dateCell);
  
      const detailsCell = document.createElement("td");
      let eventHTML = `
        <h3>${event.title}</h3>
        <br>${event.description}
        <br><br><a href="${event.rsvpLink}">RSVP here!</a>
        <br><br><b>Location:</b> ${event.location}
      `;
      //<img class="card-img-top" src="${event.image}" alt="Event Image" width="50%" align="right">

      detailsCell.innerHTML = eventHTML;
      row.appendChild(detailsCell);
  
      tableBody.appendChild(row);
    });
  });
  