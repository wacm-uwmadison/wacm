// //json format:
// {
//   "date": { "month": "", "day": "", "time": "" },
//   "title": "",
//   "description": "",
//   "imgSrc": "images/",
//   "rsvpLink": "",
//   "location": ""
// },

const past_events_list = [
  {
    "date": { "month": "December", "day": "11", "time": "12:00 pm - 1:00 pm" },
    "title": "WACM BiWeekly Lunch with Google Engineer",
    "description": "Join us for our last biweekly lunch next week December 11th at 12-1pm in CS1240!! We will be having a Google engineer and UW-Madison alum as a guest speaker to share her story and experience as a woman in tech. Come get some Ian's pizza and learn!",
    "imgSrc": "images/Biweekly121124.png",
    "rsvpLink": "https://forms.gle/vSARB9pi9UwMATuT7",
    "location": "Computer Sciences 1240"
  },
  {
    "date": { "month": "December", "day": "2", "time": "4:00 pm - 5:00 pm" },
    "title": "Internship Prep Talk",
    "description": "Join our President Kayley for the annual WACM Internship Preparation Talk, designed to help students navigate the internship application process with confidence. Kayley will share tips on crafting strong resumes, preparing applications, standing out in interviews, and exploring alternative ways to gain valuable experience if your internships aren’t secured. A mini resume review will also take place—upload your resume through the RSVP form for a chance to receive personalized feedback during the session!\nThe talk will conclude with a Q&A, giving attendees an opportunity to ask questions and receive personal advice. While this event is geared toward underclassmen and WACM members, all students are welcome to attend. Don’t miss this opportunity to gain invaluable insights and jumpstart your career in the computer science major!",
    "imgSrc": "images/internshiptalks.png",
    "rsvpLink": "https://forms.gle/h9MjaUG5h4ntvLoL8",
    "location": "Computer Sciences 1240"
  },
  {
    "date": { "month": "November", "day": "13", "time": "1:00 pm - 2:00 pm CT" },
    "title": "Biweekly Lunch: Course Edition",
    "description": "Hello everyone! We hope you are all taking time to yourself today and taking care of your mental health. We are here for any support always. Here is our flyer for our next Biweekly Lunch next week Wednesday Nov. 13 1-2pm!! We hope to see everyone there as we'll be connecting with each other about classes and asking for course enrollment advice. + Free Lunch!!!",
    "imgSrc": "images/biweekly111324.png",
    "rsvpLink": "https://forms.gle/AbwbXvT2VQK9VnhB6",
    "location": "Computer Sciences 3310"
  },
  {
    "date": { "month": "November", "day": "8", "time": "4:00 pm" },
    "title": "WACM x WAISI Trivia Night",
    "description": "Come join us and WAISI, the Wisconsin AI Safety Initiative, for some free pizza and the chance to win some gift cards Perfect for using for yourself, or regifting for the holidays!\n This event will be hosted November 8th at 4PM in CS1240.",
    "imgSrc": "images/CSTriviaNight.png",
    "rsvpLink": "https://docs.google.com/forms/d/e/1FAIpQLSe4ikD5lLhIbxhYgRZv9T4Gfc0aetBZRxI0-FVhEPv5sGxcMw/viewform",
    "location": "Computer Sciences 1240"
  },
  {
    "date": { "month": "November", "day": "1", "time": "4:30 pm - 5:30 pm CT" },
    "title": "Halloween Cookie Decorating",
    "description": "A Halloween cookie decorating social!",
    "imgSrc": "images/cookiedecorating.png",
    "rsvpLink": "https://docs.google.com/forms/d/e/1FAIpQLScZBSqADWyXW-WWbr0gYAaLr1C6jBQZaTD9Zi-QCvvt6G4rLg/viewform",
    "location": "Computer Sciences 2310"
  },
  {
    "date": { "month": "October", "day": "16", "time": "1:00 pm - 2:00 pm CT" },
    "title": "WACM Biweekly-Lunch",
    "description": "Get help on perfecting your resume and/or LinkedIn !!",
    "imgSrc": "images/biweekly101624.png",
    "rsvpLink": "https://forms.gle/w5A8AqJZNYxNE8v9A",
    "location": "Computer Sciences 3310"
  },
  {
    "date": { "month": "October", "day": "4", "time": "5:00 pm CT" },
    "title": "WACM Kick-Off Event",
    "description": "We're having our official WACM kick-off event this Friday, Oct 4th at 5PM in the Union South Agriculture room. Come get some pizza and make friendship bracelets with us!\nRSVP by Oct 2nd using the QR code or the link in our Linktree! We hope to see you all there :)",
    "imgSrc": "images/kickoff2024.png",
    "rsvpLink": "https://docs.google.com/forms/d/e/1FAIpQLSeUCGTu5VDOvRBj9qV7Er9PBSaXxC93rqKF6K6JB_d76CHDrA/viewform",
    "location": "Union South Agriculture Room (3rd floor)"
  }
  ];
  