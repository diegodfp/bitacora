document.getElementById('getActivitiesForm').addEventListener('submit', function(event) {
    event.preventDefault();
    const userId = document.getElementById('userId').value;
    fetchActivities(userId);
  });
  
  function fetchActivities(userId) {
    fetch(`/user/${userId}/activities`)
      .then(response => response.json())
      .then(data => {
        console.log(data); // Muestra el objeto recibido
        displayActivities(data);
      })
      .catch(error => console.error('Error fetching activities:', error));
  }
  
  function displayActivities(activities) {
    const activitiesList = document.getElementById('activitiesList');
    activitiesList.innerHTML = '';
  
    activities.forEach(activity => {
      const activityElement = document.createElement('div');
      activityElement.classList.add('activity');
      activityElement.innerHTML = `
        <h3>${activity.activityName}</h3>
        <p><strong>Proyecto:</strong> ${activity.projectName}</p>
        <p><strong>Tipo:</strong> ${activity.activityTypeName}</p>
        <p><strong>Prioridad:</strong> ${activity.priorityLevel}</p>
        <p><strong>Estatus:</strong> ${activity.activityStatusName}</p>
        <p><strong>Descripción:</strong> ${activity.description}</p>
      `;
      activitiesList.appendChild(activityElement);
    });
  }
  
  
  function startActivity() {
    const activityId = document.getElementById('activityId').value;
    fetch(`/activities/${activityId}/start`, {
      method: 'POST'
    })
      .then(response => response.json())
      .then(data => alert('Actividad Iniciada'))
      .catch(error => console.error('Error starting activity:', error));
  }
  
  function pauseActivity() {
    const activityId = document.getElementById('activityId').value;
    fetch(`/activities/${activityId}/pause`, {
      method: 'POST'
    })
      .then(response => response.json())
      .then(data => alert('Actividad Pausada'))
      .catch(error => console.error('Error pausing activity:', error));
  }
  
  function completeActivity() {
    const activityId = document.getElementById('activityId').value;
    fetch(`/activities/${activityId}/complete`, {
      method: 'POST'
    })
      .then(response => response.json())
      .then(data => alert('Actividad Finalizada'))
      .catch(error => console.error('Error completing activity:', error));
  }
  