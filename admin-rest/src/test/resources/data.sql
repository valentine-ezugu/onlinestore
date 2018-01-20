INSERT INTO user VALUES
  (1, 'admin@yahoo.com', 'TRUE', NULL, NULL, '$2a$12$LtbYtEzOBdnsiD/E9Wtj2OZz80wXDrKDMacktYr8pq3j0X6HO75Fm', NULL, 'admin'),
  (2, 'valentineezugu@yahoo.com', 'TRUE', 'Valentine', 'Ezugu', '$2a$12$LtbYtEzOBdnsiD/E9Wtj2Oj7SuEU4Z0YOHffjdDm2t01lMnQLyeCe', NULL, 'V'),
  (3, 'egbusonltd@yahoo.com', 'TRUE', NULL, NULL, '$2a$12$LtbYtEzOBdnsiD/E9Wtj2OsFAal5Zn4UPZpYT22PorhapAGnanfWq', NULL, 'ema');

INSERT INTO password_reset_token VALUES (1, '2017-10-01 16:27:48', '59bdd703-c3e4-4a59-b0b8-cbc936f47fbf', 3);

INSERT INTO role VALUES (1,'ADMIN'), (2,'USER');

INSERT INTO shopping_cart VALUES (1, NULL, 2), (2, NULL, 3);

INSERT INTO user_role VALUES (1, 1), (2, 2), (3, 2);


INSERT INTO `book` (`id`, `active`, `author`, `category`, `description`, `format`, `in_stock_number`, `isbn`, `language`, `list_price`, `number_of_pages`, `our_price`, `publication_date`, `publisher`, `shipping_weight`, `title`)
VALUES
  (1, 'TRUE', 'Miles Davis', 'Arts & Literature',
      '<p><span style=\"color: #333333; font-family: Arial, sans-serif;\">(Guitar Solo). 15 jazzy solo guitar arrangements of Davis classics, including: All Blues * All of You * Blue in Green * Bye Bye Blackbird * Four * Freddie Freeloader * I Could Write a Book * Milestones * Nardis * Nefertiti * Seven Steps to Heaven * So What * Solar * There Is No Greater Love * When I Fall in Love. The CD includes full demos of each piece by Jamie Findlay.</span></p>',
      'paperback', 22, 634023020, 'english', 21.95, 96, 17.51, '2006-02-01', 'Hal Leonard', 12,
   'Miles Davis for Solo Guitar'),

  (2, 'TRUE', ' Leonardo Da Vinci', 'Arts & Literature', '<p style=\"margin: 0px 0px 14px; padding: 0px; color: #333333; font-family: Arial, sans-serif;\"><strong>An all-new, jewel-like, reader-friendly format gives new life to this relaunch of an international best-seller.</strong></p>\r\n<p style=\"margin: -4px 0px 14px; padding: 0px; color: #333333; font-family: Arial, sans-serif;\">&nbsp;</p>\r\n<p style=\"margin: -4px 0px 14px; padding: 0px; color: #333333; font-family: Arial, sans-serif;\">Leonardo da Vinci\s artist, inventor, and prototypical Renaissance man?is a perennial source of fascination because of his astonishing intellect and boundless curiosity about the natural and man-made world. During his life he created numerous works of art and kept voluminous notebooks that detailed his artistic and intellectual pursuits.</p>\r\n<p style=\"margin: -4px 0px 14px; padding: 0px; color: #333333; font-family: Arial, sans-serif;\">&nbsp;</p>\r\n<p style=\"margin: -4px 0px 14px; padding: 0px; color: #333333; font-family: Arial, sans-serif;\">The collection of writings and art in this magnificent book are drawn from his notebooks. The book organizes his wide range of interests into subjects such as human figures, light and shade, perspective and visual perception, anatomy, botany and landscape, geography, the physical sciences and astronomy, architecture, sculpture, and inventions. Nearly every piece of writing throughout the book is keyed to the piece of artwork it describes.</p>\r\n<p style=\"margin: -4px 0px 14px; padding: 0px; color: #333333; font-family: Arial, sans-serif;\">&nbsp;</p>\r\n<p style=\"margin: -4px 0px 0px; padding: 0px; color: #333333; font-family: Arial, sans-serif;\">The writing and art is selected by art historian H. Anna Suh, who provides fascinating commentary and insight into the material, making&nbsp;<em>Leonardo\s Notebooks</em>&nbsp;an exquisite single-volume compendium celebrating his enduring genius.</p>', 'paperback', 34, 1579129463, 'english', 19.99, 448, 17.06, '2013-09-24', 'Black Dog & Leventhal', 2, 'Leonardo\s Notebooks: Writing and Art of the Great Master'),
  (3, 'TRUE', ' Benjamin Spock M.D.', 'Arts & Literature', '<p><span style=\"color: #333333; font-family: Arial, sans-serif;\">Dr. Benjamin Spock is America&rsquo;s most trusted name in child care and parenting, and his essential guidebook has topped bestseller lists for over sixty-five years. This fully revised and updated edition of the classic manual provides first time and experienced parents the best information about caring for new babies, toddlers, and adolescents in the twenty-first century.</span><br style=\"color: #333333; font-family: Arial, sans-serif;\" /><br style=\"color: #333333; font-family: Arial, sans-serif;\" /><span style=\"color: #333333; font-family: Arial, sans-serif;\">All of Dr. Spock&rsquo;s invaluable, time-tested advice is here, along with the most current medical practices and advances in health care. While still covering key parenting issues like accidents, illnesses and injuries, this edition of the classic also contains the latest on:</span><br style=\"color: #333333; font-family: Arial, sans-serif;\" /><br style=\"color: #333333; font-family: Arial, sans-serif;\" /><span style=\"color: #333333; font-family: Arial, sans-serif;\">* Immunizations</span><br style=\"color: #333333; font-family: Arial, sans-serif;\" /><span style=\"color: #333333; font-family: Arial, sans-serif;\">* Nutrition and childhood obesity</span><br style=\"color: #333333; font-family: Arial, sans-serif;\" /><span style=\"color: #333333; font-family: Arial, sans-serif;\">* Cultural diversity</span><br style=\"color: #333333; font-family: Arial, sans-serif;\" /><span style=\"color: #333333; font-family: Arial, sans-serif;\">* Alternative and non-traditional family structures</span><br style=\"color: #333333; font-family: Arial, sans-serif;\" /><span style=\"color: #333333; font-family: Arial, sans-serif;\">* Children&rsquo;s learning and brain development</span><br style=\"color: #333333; font-family: Arial, sans-serif;\" /><span style=\"color: #333333; font-family: Arial, sans-serif;\">* Raising children with special needs</span><br style=\"color: #333333; font-family: Arial, sans-serif;\" /><span style=\"color: #333333; font-family: Arial, sans-serif;\">* Environmental health</span><br style=\"color: #333333; font-family: Arial, sans-serif;\" /><span style=\"color: #333333; font-family: Arial, sans-serif;\">* Increasingly com.valentine.common disorders such as ADHD, childhood depression, and autism&mdash;including medications and behavioral interventions</span><br style=\"color: #333333; font-family: Arial, sans-serif;\" /><span style=\"color: #333333; font-family: Arial, sans-serif;\">* Children and the media, including screen time, video games, and the internet</span><br style=\"color: #333333; font-family: Arial, sans-serif;\" /><br style=\"color: #333333; font-family: Arial, sans-serif;\" /><span style=\"color: #333333; font-family: Arial, sans-serif;\">Updated by leading pediatrician Robert Needlman, the book includes a revised glossary of com.valentine.common medications and a resource guide that compiles the most reliable online resources. This indispensable guide is still the next best thing to Dr. Spock&rsquo;s #1 rule of parenting: &ldquo;Trust yourself. You know more than you think you do.&rdquo;</span></p>', 'paperback', 44, 1439189293, 'english', 12.3, 1168, 8.71, '2011-12-27', 'Pocket Books', 1, 'Dr. Spock\s Baby and Child Care: 9th Edition'),
  (5, 'TRUE', ' Danielle Krysa', 'Arts & Literature', '<p><span style=\"color: #333333; font-family: Arial, sans-serif;\">This book is duct tape for the mouth of every artists inner critic. Silencing that stifling voice once and for all, this salve for creatives introduces ten truths they must face in order to defeat self-doubt. Each encouraging chapter deconstructs a pivotal moment on the path to success&mdash;fear of the blank page, the dangers of jealousy, sharing work with others&mdash;and explains how to navigate roadblock. Packed with helpful anecdotes, thoughts from successful creatives, and practical exercises gleaned from Danielle Krysa\s years of working with professional and aspiring artists&mdash;plus riotously apt illustrations from art world darling Martha Rich&mdash;this book arms readers with the most essential tool for their toolbox: the confidence they need to get down to business and make good work.</span></p>', 'hardcover', 65, 1452148449, 'english', 16.95, 136, 12.55, '2016-10-11', 'Chronicle Books', 14, 'Your Inner Critic Is a Big Jerk: And Other Truths About Being Creative'),
  (6, 'TRUE', ' Loren B. Belker', 'Management', '<p><span style=\"color: #333333; font-family: Arial, sans-serif;\">What\s a rookie manager to do? Faced with new responsibilities, and in need of quick, dependable guidance, novice managers can\t afford to learn by trial and error.&nbsp;</span><em style=\"color: #333333; font-family: Arial, sans-serif;\">The First-Time Manager</em><span style=\"color: #333333; font-family: Arial, sans-serif;\">&nbsp;is the answer, dispensing the bottom-line wisdom they need to succeed. A true management classic, the book covers essential topics such as hiring and firing, leadership, motivation, managing time, dealing with superiors, and much more.</span></p>\r\n<p style=\"margin: 0px 0px 14px; padding: 0px; color: #333333; font-family: Arial, sans-serif;\">&nbsp;</p>\r\n<p><span style=\"color: #333333; font-family: Arial, sans-serif;\">Written in an inviting and accessible style, the revised sixth edition includes new material on increasing employee engagement, encouraging innovation and initiative, helping team members optimize their talents, improving outcomes, and distinguishing oneself as a leader.</span></p>\r\n<p style=\"margin: -4px 0px 14px; padding: 0px; color: #333333; font-family: Arial, sans-serif;\">&nbsp;</p>\r\n<p><span style=\"color: #333333; font-family: Arial, sans-serif;\">Packed with immediately usable insight on everything from building a team environment to conducting performance appraisals,&nbsp;</span><em style=\"color: #333333; font-family: Arial, sans-serif;\">The First-Time Manager</em><span style=\"color: #333333; font-family: Arial, sans-serif;\">&nbsp;remains the ultimate guide for anyone starting his or her career in management.</span></p>', 'paperback', 47, 814417833, 'english', 17.95, 240, 10, '2012-01-03', 'AMACOM', 14, 'The First-Time Manager'),
  (7, 'TRUE', 'Dale Carnegie', 'Management', '<p><span style=\"color: #333333; font-family: Arial, sans-serif;\">For more than sixty years the rock-solid, time-tested advice in this book has carried thousands of now famous people up the ladder of success in their business and personal lives.</span></p>\r\n<p style=\"margin: 0px 0px 14px; padding: 0px; color: #333333; font-family: Arial, sans-serif;\">Now this previously revised and updated bestseller is available in trade paperback for the first time to help you achieve your maximum potential throughout the next century! Learn:</p>\r\n<p style=\"margin: -4px 0px 14px; padding: 0px; color: #333333; font-family: Arial, sans-serif;\">* Three fundamental techniques in handling people</p>\r\n<p style=\"margin: -4px 0px 14px; padding: 0px; color: #333333; font-family: Arial, sans-serif;\">* The six ways to make people like you</p>\r\n<p style=\"margin: -4px 0px 14px; padding: 0px; color: #333333; font-family: Arial, sans-serif;\">* The twelve ways to win people to you way of thinking</p>\r\n<p style=\"margin: -4px 0px 0px; padding: 0px; color: #333333; font-family: Arial, sans-serif;\">* The nine ways to change people without arousing resentment</p>', 'paperback', 54, 671027034, 'english', 16, 288, 10, '1998-10-01', 'Pocket Books', 7, 'How to Win Friends & Influence People'),
  (8, 'TRUE', ' Bruce Tulgan', 'Management', '<p style=\"margin: 0px 0px 14px; padding: 0px; color: #333333; font-family: Arial, sans-serif;\">For more than twenty years, management expert Bruce Tulgan has been asking<em>, &ldquo;What are the most difficult challenges you face when it comes to managing people?&rdquo;</em></p>\r\n<p style=\"margin: -4px 0px 14px; padding: 0px; color: #333333; font-family: Arial, sans-serif;\">Regardless of industry or job title, managers cite the same core issues&mdash;27 recurring challenges: the superstar whom the manager is afraid of losing, the slacker whom the manager cannot figure out how to motivate, the one with an attitude problem, and the two who cannot get along, to name just a few.</p>\r\n<p style=\"margin: -4px 0px 14px; padding: 0px; color: #333333; font-family: Arial, sans-serif;\">It turns out that when things are going wrong in a management relationship, the com.valentine.common denominator is almost always unstructured, low substance, hit-or-miss communication.</p>\r\n<p style=\"margin: -4px 0px 14px; padding: 0px; color: #333333; font-family: Arial, sans-serif;\">The real problem is that most managers are &ldquo;managing on autopilot&rdquo; without even realizing it&mdash;until something goes wrong. And if you are managing on autopilot, then something almost always does.</p>\r\n<p style=\"margin: -4px 0px 14px; padding: 0px; color: #333333; font-family: Arial, sans-serif;\"><em>The 27 Challenges Managers Face</em>&nbsp;shows exactly how to break the vicious cycle and gain control of management relationships. No matter what the issue, Tulgan shows that the fundamentals are all you need. The very best managers hold ongoing one-on-one conversations that make expectations clear, track performance, offer feedback, and hold people accountable.</p>\r\n<p style=\"margin: -4px 0px 14px; padding: 0px; color: #333333; font-family: Arial, sans-serif;\">For every workplace problem&mdash;even the most awkward and difficult&mdash;<em>The 27 Challenges Managers Face</em>&nbsp;shows how to tailor conversations to solve situations familiar to every manager. Tulgan offers clear approaches for turning around bad attitudes, reducing friction and conflict, improving low performers, retaining top performers, and even addressing your own personal burnout.</p>\r\n<p style=\"margin: -4px 0px 0px; padding: 0px; color: #333333; font-family: Arial, sans-serif;\"><em>The 27 Challenges Managers Face</em>&nbsp;is an indispensable resource for managers at all levels, one anyone managing anyone will want to keep on hand. One challenge at a time, you&rsquo;ll see how the most effective managers use the fundamentals of management to proactively resolve (nearly) any problem a manager could face.&nbsp;</p>', 'hardcover', 32, 111872559, 'english', 28, 256, 18.4, '2014-09-09', 'Jossey-Bass', 28, 'The 27 Challenges Managers Face: Step-by-Step Solutions to (Nearly) All of Your Management Problems'),
  (9, 'TRUE', ' Paul Scherz', 'Engineering', '<p style=\"margin: 0px 0px 14px; padding: 0px; color: #333333; font-family: Arial, sans-serif;\"><strong>A Fully-Updated, No-Nonsense Guide to Electronics</strong></p>\r\n<p style=\"margin: -4px 0px 14px; padding: 0px; color: #333333; font-family: Arial, sans-serif;\">Advance your electronics knowledge and gain the skills necessary to develop and construct your own functioning gadgets. Written by a pair of experienced engineers and dedicated hobbyists,&nbsp;<em>Practical Electronics for Inventors,</em>&nbsp;Fourth Edition, lays out the essentials and provides step-by-step instructions, schematics, and illustrations. Discover how to select the right components, design and build circuits, use microcontrollers and ICs, work with the latest software tools, and test and tweak your creations. This easy-to-follow book features new instruction on programmable logic, semiconductors, operational amplifiers, voltage regulators, power supplies, digital electronics, and more.<br />&nbsp;<br /><em>Practical Electronics for Inventors,</em>&nbsp;Fourth Edition, covers:</p>\r\n<ul style=\"padding: 0px; color: #333333; font-family: Arial, sans-serif; margin: 0px 0px 0px !important 20px;\">\r\n<li style=\"margin: 0px 0px 0px 20px; word-wrap: break-word;\">Resistors, capacitors, inductors, and transformers</li>\r\n<li style=\"margin: 0px 0px 0px 20px; word-wrap: break-word;\">Diodes, transistors, and integrated circuits</li>\r\n<li style=\"margin: 0px 0px 0px 20px; word-wrap: break-word;\">Optoelectronics, solar cells, and phototransistors</li>\r\n<li style=\"margin: 0px 0px 0px 20px; word-wrap: break-word;\">Sensors, GPS modules, and touch screens</li>\r\n<li style=\"margin: 0px 0px 0px 20px; word-wrap: break-word;\">Op amps, regulators, and power supplies</li>\r\n<li style=\"margin: 0px 0px 0px 20px; word-wrap: break-word;\">Digital electronics, LCD displays, and logic gates</li>\r\n<li style=\"margin: 0px 0px 0px 20px; word-wrap: break-word;\">Microcontrollers and prototyping platforms</li>\r\n<li style=\"margin: 0px 0px 0px 20px; word-wrap: break-word;\">Combinational and sequential programmable logic</li>\r\n<li style=\"margin: 0px 0px 0px 20px; word-wrap: break-word;\">DC motors, RC servos, and stepper motors</li>\r\n<li style=\"margin: 0px 0px 0px 20px; word-wrap: break-word;\">Microphones, audio amps, and speakers</li>\r\n<li style=\"margin: 0px 0px 0px 20px; word-wrap: break-word;\">Modular electronics and prototypes</li>\r\n</ul>', 'paperback', 22, 1259587541, 'english', 40, 1056, 22.9, '2016-03-24', 'McGraw-Hill Education TAB', 5, 'Practical Electronics for Inventors, Fourth Edition'),
  (10, 'TRUE', 'Jones & Bartlett Learning', 'Engineering', '<p><span style=\"color: #333333; font-family: Arial, sans-serif;\">Ugly\s Electrical References, 2014 Edition is designed to be used as an on-the-job reference. Used worldwide by electricians, engineers, contractors, designers, maintenance workers, instructors, and the military; Ugly\s contains the most commonly required electrical information in an easy-to-read and easy-to-access format. Ugly\s presents a succinct portrait of the most pertinent information all electricians need at their fingertips, including: mathematical formulas, National Electrical Code tables, wiring configurations, conduit bending, voltage drops, and life-saving first aid procedures.</span></p>', 'hardcover', 12, 1449690777, 'english', 21.95, 198, 14.93, '2014-03-21', 'Jones & Bartlett Learning', 6, 'Ugly\s Electrical References, 2014 Edition'),
  (11, 'TRUE', 'William Kent Krueger', 'Fiction', '<p><em style=\"color: #333333; font-family: Arial, sans-serif;\">NEW YORK TIMES&nbsp;</em><span style=\"color: #333333; font-family: Arial, sans-serif;\">BESTSELLER</span><br style=\"color: #333333; font-family: Arial, sans-serif;\" /><span style=\"color: #333333; font-family: Arial, sans-serif;\">WINNER OF THE 2014 EDGAR AWARD FOR BEST NOVEL</span><br style=\"color: #333333; font-family: Arial, sans-serif;\" /><span style=\"color: #333333; font-family: Arial, sans-serif;\">WINNER OF THE 2014 DILYS AWARD</span><br style=\"color: #333333; font-family: Arial, sans-serif;\" /><span style=\"color: #333333; font-family: Arial, sans-serif;\">A&nbsp;</span><em style=\"color: #333333; font-family: Arial, sans-serif;\">SCHOOL LIBRARY JOURNAL&nbsp;</em><span style=\"color: #333333; font-family: Arial, sans-serif;\">BEST BOOK OF 2013</span><br style=\"color: #333333; font-family: Arial, sans-serif;\" /><br style=\"color: #333333; font-family: Arial, sans-serif;\" /><em style=\"color: #333333; font-family: Arial, sans-serif;\">&ldquo;That was it. That was all of it. A grace so ordinary there was no reason at all to remember it. Yet I have never across the forty years since it was spoken forgotten a single word.&rdquo;&nbsp;</em><br style=\"color: #333333; font-family: Arial, sans-serif;\" /><br style=\"color: #333333; font-family: Arial, sans-serif;\" /><span style=\"color: #333333; font-family: Arial, sans-serif;\">New Bremen, Minnesota, 1961. The Twins were playing their debut season, ice-cold root beers were selling out at the soda counter of Halderson&rsquo;s Drugstore, and Hot Stuff comic books were a mainstay on every barbershop magazine rack. It was a time of innocence and hope for a country with a new, young president. But for thirteen-year-old Frank Drum it was a grim summer in which death visited frequently and assumed many forms. Accident. Nature. Suicide. Murder.&nbsp;</span><br style=\"color: #333333; font-family: Arial, sans-serif;\" /><br style=\"color: #333333; font-family: Arial, sans-serif;\" /><span style=\"color: #333333; font-family: Arial, sans-serif;\">Frank begins the season preoccupied with the concerns of any teenage boy, but when tragedy unexpectedly strikes his family&mdash;which includes his Methodist minister father; his passionate, artistic mother; Juilliard-bound older sister; and wise-beyond-his-years kid brother&mdash;he finds himself thrust into an adult world full of secrets, lies, adultery, and betrayal, suddenly called upon to demonstrate a maturity and gumption beyond his years.&nbsp;</span><br style=\"color: #333333; font-family: Arial, sans-serif;\" /><br style=\"color: #333333; font-family: Arial, sans-serif;\" /><span style=\"color: #333333; font-family: Arial, sans-serif;\">Told from Frank&rsquo;s perspective forty years after that fateful summer,&nbsp;</span><em style=\"color: #333333; font-family: Arial, sans-serif;\">Ordinary Grace</em><span style=\"color: #333333; font-family: Arial, sans-serif;\">&nbsp;is a brilliantly moving account of a boy standing at the door of his young manhood, trying to understand a world that seems to be falling apart around him. It is an unforgettable novel about discovering the terrible price of wisdom and the enduring grace of God.</span></p>', 'paperback', 23, 1451645856, 'english', 16, 336, 10, '2014-03-04', 'Atria Books', 10, 'Ordinary Grace'),
  (12, 'TRUE', ' Herbert Schildt', 'Programming', '<h4 style=\"margin: 0px; padding: 0px 0px 4px; font-family: Arial, sans-serif; text-rendering: optimizeLegibility; color: #333333;\">Essential Java Programming Skills--Made Easy!</h4>\r\n<p style=\"margin: 0px 0px 14px; padding: 0px; color: #333333; font-family: Arial, sans-serif;\">Fully updated for Java Platform, Standard Edition 8 (Java SE 8),&nbsp;<em>Java: A Beginner\s Guide</em>, Sixth Edition gets you started programming in Java right away. Bestselling programming author Herb Schildt begins with the basics, such as how to create, compile, and run a Java program. He then moves on to the keywords, syntax, and constructs that form the core of the Java language. This Oracle Press resource also covers some of the more advanced features, including multithreaded programming, generics, and Swing. Of course, new Java SE 8 features such as lambda expressions and default interface methods are described. An introduction to JavaFX, Java\s newest GUI, concludes this step-by-step tutorial.</p>\r\n<p style=\"margin: -4px 0px 14px; padding: 0px; color: #333333; font-family: Arial, sans-serif;\"><strong>Designed for Easy Learning:</strong></p>\r\n<ul style=\"margin: 0px 0px 18px 20px; padding: 0px; color: #333333; font-family: Arial, sans-serif;\">\r\n<li style=\"margin: 0px 0px 0px 20px; word-wrap: break-word;\">Key Skills &amp; Concepts -- Chapter-opening lists of specific skills covered in the chapter</li>\r\n<li style=\"margin: 0px 0px 0px 20px; word-wrap: break-word;\">Ask the Expert -- Q&amp;A sections filled with bonus information and helpful tips</li>\r\n<li style=\"margin: 0px 0px 0px 20px; word-wrap: break-word;\">Try This -- Hands-on exercises that show you how to apply your skills</li>\r\n<li style=\"margin: 0px 0px 0px 20px; word-wrap: break-word;\">Self Tests -- End-of-chapter quizzes to reinforce your skills</li>\r\n<li style=\"margin: 0px 0px 0px 20px; word-wrap: break-word;\">Annotated Syntax -- Example code with commentary that describes the programming techniques being illustrated</li>\r\n</ul>\r\n<p style=\"margin: 0px 0px 14px; padding: 0px; color: #333333; font-family: Arial, sans-serif;\">&nbsp;</p>\r\n<p style=\"margin: -4px 0px 0px; padding: 0px; color: #333333; font-family: Arial, sans-serif;\">The book\s code examples are available FREE for download.</p>', 'paperback', 33, 71809252, 'english', 40, 728, 20.83, '2014-05-06', 'McGraw-Hill Education', 3, 'Java: A Beginner\s Guide, Sixth Edition');


