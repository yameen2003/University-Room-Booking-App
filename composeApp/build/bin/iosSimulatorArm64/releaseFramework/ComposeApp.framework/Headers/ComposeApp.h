#import <Foundation/NSArray.h>
#import <Foundation/NSDictionary.h>
#import <Foundation/NSError.h>
#import <Foundation/NSObject.h>
#import <Foundation/NSSet.h>
#import <Foundation/NSString.h>
#import <Foundation/NSValue.h>

@class ComposeAppUser, ComposeAppAuthState, ComposeAppRoom_runtimeMigration, ComposeAppRoom_runtimeInvalidationTracker, ComposeAppRoom_runtimeRoomDatabase, ComposeAppBookingDatabase, ComposeAppRoom_runtimeRoomOpenDelegate, ComposeAppPerson, ComposeAppPeopleDao_ImplCompanion, ComposeAppPeopleDatabase, ComposeAppBooking, ComposeAppBookingDao_ImplCompanion, ComposeAppComputer, ComposeAppComputerDao_ImplCompanion, ComposeAppRoom, ComposeAppRoomDao_ImplCompanion, ComposeAppUserDao_ImplCompanion, UIViewController, ComposeAppKotlinArray<T>, ComposeAppRoom_runtimeInvalidationTrackerObserver, ComposeAppRoom_runtimeRoomOpenDelegateValidationResult, ComposeAppKotlinThrowable, ComposeAppKotlinException, ComposeAppKotlinRuntimeException, ComposeAppKotlinIllegalStateException, ComposeAppKotlinByteArray, ComposeAppKotlinByteIterator;

@protocol ComposeAppPlatform, ComposeAppKotlinKClass, ComposeAppRoom_runtimeAutoMigrationSpec, ComposeAppRoom_runtimeRoomOpenDelegateMarker, ComposeAppSqliteSQLiteConnection, ComposeAppBookingDao, ComposeAppComputerDao, ComposeAppRoomDao, ComposeAppUserDao, ComposeAppKotlinx_coroutines_coreFlow, ComposeAppPeopleDao, ComposeAppKotlinKDeclarationContainer, ComposeAppKotlinKAnnotatedElement, ComposeAppKotlinKClassifier, ComposeAppSqliteSQLiteStatement, ComposeAppKotlinx_coroutines_coreFlowCollector, ComposeAppKotlinIterator;

NS_ASSUME_NONNULL_BEGIN
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Wunknown-warning-option"
#pragma clang diagnostic ignored "-Wincompatible-property-type"
#pragma clang diagnostic ignored "-Wnullability"

#pragma push_macro("_Nullable_result")
#if !__has_feature(nullability_nullable_result)
#undef _Nullable_result
#define _Nullable_result _Nullable
#endif

__attribute__((swift_name("KotlinBase")))
@interface ComposeAppBase : NSObject
- (instancetype)init __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
+ (void)initialize __attribute__((objc_requires_super));
@end

@interface ComposeAppBase (ComposeAppBaseCopying) <NSCopying>
@end

__attribute__((swift_name("KotlinMutableSet")))
@interface ComposeAppMutableSet<ObjectType> : NSMutableSet<ObjectType>
@end

__attribute__((swift_name("KotlinMutableDictionary")))
@interface ComposeAppMutableDictionary<KeyType, ObjectType> : NSMutableDictionary<KeyType, ObjectType>
@end

@interface NSError (NSErrorComposeAppKotlinException)
@property (readonly) id _Nullable kotlinException;
@end

__attribute__((swift_name("KotlinNumber")))
@interface ComposeAppNumber : NSNumber
- (instancetype)initWithChar:(char)value __attribute__((unavailable));
- (instancetype)initWithUnsignedChar:(unsigned char)value __attribute__((unavailable));
- (instancetype)initWithShort:(short)value __attribute__((unavailable));
- (instancetype)initWithUnsignedShort:(unsigned short)value __attribute__((unavailable));
- (instancetype)initWithInt:(int)value __attribute__((unavailable));
- (instancetype)initWithUnsignedInt:(unsigned int)value __attribute__((unavailable));
- (instancetype)initWithLong:(long)value __attribute__((unavailable));
- (instancetype)initWithUnsignedLong:(unsigned long)value __attribute__((unavailable));
- (instancetype)initWithLongLong:(long long)value __attribute__((unavailable));
- (instancetype)initWithUnsignedLongLong:(unsigned long long)value __attribute__((unavailable));
- (instancetype)initWithFloat:(float)value __attribute__((unavailable));
- (instancetype)initWithDouble:(double)value __attribute__((unavailable));
- (instancetype)initWithBool:(BOOL)value __attribute__((unavailable));
- (instancetype)initWithInteger:(NSInteger)value __attribute__((unavailable));
- (instancetype)initWithUnsignedInteger:(NSUInteger)value __attribute__((unavailable));
+ (instancetype)numberWithChar:(char)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedChar:(unsigned char)value __attribute__((unavailable));
+ (instancetype)numberWithShort:(short)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedShort:(unsigned short)value __attribute__((unavailable));
+ (instancetype)numberWithInt:(int)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedInt:(unsigned int)value __attribute__((unavailable));
+ (instancetype)numberWithLong:(long)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedLong:(unsigned long)value __attribute__((unavailable));
+ (instancetype)numberWithLongLong:(long long)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedLongLong:(unsigned long long)value __attribute__((unavailable));
+ (instancetype)numberWithFloat:(float)value __attribute__((unavailable));
+ (instancetype)numberWithDouble:(double)value __attribute__((unavailable));
+ (instancetype)numberWithBool:(BOOL)value __attribute__((unavailable));
+ (instancetype)numberWithInteger:(NSInteger)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedInteger:(NSUInteger)value __attribute__((unavailable));
@end

__attribute__((swift_name("KotlinByte")))
@interface ComposeAppByte : ComposeAppNumber
- (instancetype)initWithChar:(char)value;
+ (instancetype)numberWithChar:(char)value;
@end

__attribute__((swift_name("KotlinUByte")))
@interface ComposeAppUByte : ComposeAppNumber
- (instancetype)initWithUnsignedChar:(unsigned char)value;
+ (instancetype)numberWithUnsignedChar:(unsigned char)value;
@end

__attribute__((swift_name("KotlinShort")))
@interface ComposeAppShort : ComposeAppNumber
- (instancetype)initWithShort:(short)value;
+ (instancetype)numberWithShort:(short)value;
@end

__attribute__((swift_name("KotlinUShort")))
@interface ComposeAppUShort : ComposeAppNumber
- (instancetype)initWithUnsignedShort:(unsigned short)value;
+ (instancetype)numberWithUnsignedShort:(unsigned short)value;
@end

__attribute__((swift_name("KotlinInt")))
@interface ComposeAppInt : ComposeAppNumber
- (instancetype)initWithInt:(int)value;
+ (instancetype)numberWithInt:(int)value;
@end

__attribute__((swift_name("KotlinUInt")))
@interface ComposeAppUInt : ComposeAppNumber
- (instancetype)initWithUnsignedInt:(unsigned int)value;
+ (instancetype)numberWithUnsignedInt:(unsigned int)value;
@end

__attribute__((swift_name("KotlinLong")))
@interface ComposeAppLong : ComposeAppNumber
- (instancetype)initWithLongLong:(long long)value;
+ (instancetype)numberWithLongLong:(long long)value;
@end

__attribute__((swift_name("KotlinULong")))
@interface ComposeAppULong : ComposeAppNumber
- (instancetype)initWithUnsignedLongLong:(unsigned long long)value;
+ (instancetype)numberWithUnsignedLongLong:(unsigned long long)value;
@end

__attribute__((swift_name("KotlinFloat")))
@interface ComposeAppFloat : ComposeAppNumber
- (instancetype)initWithFloat:(float)value;
+ (instancetype)numberWithFloat:(float)value;
@end

__attribute__((swift_name("KotlinDouble")))
@interface ComposeAppDouble : ComposeAppNumber
- (instancetype)initWithDouble:(double)value;
+ (instancetype)numberWithDouble:(double)value;
@end

__attribute__((swift_name("KotlinBoolean")))
@interface ComposeAppBoolean : ComposeAppNumber
- (instancetype)initWithBool:(BOOL)value;
+ (instancetype)numberWithBool:(BOOL)value;
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Greeting")))
@interface ComposeAppGreeting : ComposeAppBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (NSString *)greet __attribute__((swift_name("greet()")));
@end

__attribute__((swift_name("Platform")))
@protocol ComposeAppPlatform
@required
@property (readonly) NSString *name __attribute__((swift_name("name")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("IOSPlatform")))
@interface ComposeAppIOSPlatform : ComposeAppBase <ComposeAppPlatform>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@property (readonly) NSString *name __attribute__((swift_name("name")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("AuthState")))
@interface ComposeAppAuthState : ComposeAppBase
- (instancetype)initWithIsLoggedIn:(BOOL)isLoggedIn currentUser:(ComposeAppUser * _Nullable)currentUser isLoading:(BOOL)isLoading error:(NSString * _Nullable)error __attribute__((swift_name("init(isLoggedIn:currentUser:isLoading:error:)"))) __attribute__((objc_designated_initializer));
- (ComposeAppAuthState *)doCopyIsLoggedIn:(BOOL)isLoggedIn currentUser:(ComposeAppUser * _Nullable)currentUser isLoading:(BOOL)isLoading error:(NSString * _Nullable)error __attribute__((swift_name("doCopy(isLoggedIn:currentUser:isLoading:error:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) ComposeAppUser * _Nullable currentUser __attribute__((swift_name("currentUser")));
@property (readonly) NSString * _Nullable error __attribute__((swift_name("error")));
@property (readonly) BOOL isLoading __attribute__((swift_name("isLoading")));
@property (readonly) BOOL isLoggedIn __attribute__((swift_name("isLoggedIn")));
@end

__attribute__((swift_name("Room_runtimeRoomDatabase")))
@interface ComposeAppRoom_runtimeRoomDatabase : ComposeAppBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (void)close __attribute__((swift_name("close()")));

/**
 * @note annotations
 *   androidx.annotation.RestrictTo(value=[Scope.LIBRARY_GROUP])
*/
- (NSArray<ComposeAppRoom_runtimeMigration *> *)createAutoMigrationsAutoMigrationSpecs:(NSDictionary<id<ComposeAppKotlinKClass>, id<ComposeAppRoom_runtimeAutoMigrationSpec>> *)autoMigrationSpecs __attribute__((swift_name("createAutoMigrations(autoMigrationSpecs:)")));

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (ComposeAppRoom_runtimeInvalidationTracker *)createInvalidationTracker __attribute__((swift_name("createInvalidationTracker()")));

/**
 * @note annotations
 *   androidx.annotation.RestrictTo(value=[Scope.LIBRARY_GROUP])
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (id<ComposeAppRoom_runtimeRoomOpenDelegateMarker>)createOpenDelegate __attribute__((swift_name("createOpenDelegate()")));

/**
 * @note annotations
 *   androidx.annotation.RestrictTo(value=[Scope.LIBRARY_GROUP])
*/
- (NSSet<id<ComposeAppKotlinKClass>> *)getRequiredAutoMigrationSpecClasses __attribute__((swift_name("getRequiredAutoMigrationSpecClasses()")));

/**
 * @note annotations
 *   androidx.annotation.RestrictTo(value=[Scope.LIBRARY_GROUP])
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (NSDictionary<id<ComposeAppKotlinKClass>, NSArray<id<ComposeAppKotlinKClass>> *> *)getRequiredTypeConverterClasses __attribute__((swift_name("getRequiredTypeConverterClasses()")));

/**
 * @note annotations
 *   androidx.annotation.RestrictTo(value=[Scope.LIBRARY_GROUP])
*/
- (id)getTypeConverterKlass:(id<ComposeAppKotlinKClass>)klass __attribute__((swift_name("getTypeConverter(klass:)")));

/**
 * @note annotations
 *   androidx.annotation.RestrictTo(value=[Scope.LIBRARY_GROUP_PREFIX])
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (void)internalInitInvalidationTrackerConnection:(id<ComposeAppSqliteSQLiteConnection>)connection __attribute__((swift_name("internalInitInvalidationTracker(connection:)")));
@property (readonly) ComposeAppRoom_runtimeInvalidationTracker *invalidationTracker __attribute__((swift_name("invalidationTracker")));
@end

__attribute__((swift_name("BookingDatabase")))
@interface ComposeAppBookingDatabase : ComposeAppRoom_runtimeRoomDatabase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (id<ComposeAppBookingDao>)bookingDao __attribute__((swift_name("bookingDao()")));
- (id<ComposeAppComputerDao>)computerDao __attribute__((swift_name("computerDao()")));
- (id<ComposeAppRoomDao>)roomDao __attribute__((swift_name("roomDao()")));
- (id<ComposeAppUserDao>)userDao __attribute__((swift_name("userDao()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BookingDatabase_Impl")))
@interface ComposeAppBookingDatabase_Impl : ComposeAppBookingDatabase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (id<ComposeAppBookingDao>)bookingDao __attribute__((swift_name("bookingDao()")));
- (id<ComposeAppComputerDao>)computerDao __attribute__((swift_name("computerDao()")));
- (NSArray<ComposeAppRoom_runtimeMigration *> *)createAutoMigrationsAutoMigrationSpecs:(NSDictionary<id<ComposeAppKotlinKClass>, id<ComposeAppRoom_runtimeAutoMigrationSpec>> *)autoMigrationSpecs __attribute__((swift_name("createAutoMigrations(autoMigrationSpecs:)")));

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (ComposeAppRoom_runtimeInvalidationTracker *)createInvalidationTracker __attribute__((swift_name("createInvalidationTracker()")));

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (ComposeAppRoom_runtimeRoomOpenDelegate *)createOpenDelegate __attribute__((swift_name("createOpenDelegate()")));
- (NSSet<id<ComposeAppKotlinKClass>> *)getRequiredAutoMigrationSpecClasses __attribute__((swift_name("getRequiredAutoMigrationSpecClasses()")));

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (NSDictionary<id<ComposeAppKotlinKClass>, NSArray<id<ComposeAppKotlinKClass>> *> *)getRequiredTypeConverterClasses __attribute__((swift_name("getRequiredTypeConverterClasses()")));
- (id<ComposeAppRoomDao>)roomDao __attribute__((swift_name("roomDao()")));
- (id<ComposeAppUserDao>)userDao __attribute__((swift_name("userDao()")));
@end

__attribute__((swift_name("PeopleDao")))
@protocol ComposeAppPeopleDao
@required

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)deletePerson:(ComposeAppPerson *)person completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("delete(person:completionHandler:)")));
- (id<ComposeAppKotlinx_coroutines_coreFlow>)getAllPeople __attribute__((swift_name("getAllPeople()")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)upsertPerson:(ComposeAppPerson *)person completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("upsert(person:completionHandler:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PeopleDao_Impl")))
@interface ComposeAppPeopleDao_Impl : ComposeAppBase <ComposeAppPeopleDao>
- (instancetype)initWith__db:(ComposeAppRoom_runtimeRoomDatabase *)__db __attribute__((swift_name("init(__db:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) ComposeAppPeopleDao_ImplCompanion *companion __attribute__((swift_name("companion")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)deletePerson:(ComposeAppPerson *)person completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("delete(person:completionHandler:)")));
- (id<ComposeAppKotlinx_coroutines_coreFlow>)getAllPeople __attribute__((swift_name("getAllPeople()")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)upsertPerson:(ComposeAppPerson *)person completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("upsert(person:completionHandler:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PeopleDao_Impl.Companion")))
@interface ComposeAppPeopleDao_ImplCompanion : ComposeAppBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) ComposeAppPeopleDao_ImplCompanion *shared __attribute__((swift_name("shared")));
- (NSArray<id<ComposeAppKotlinKClass>> *)getRequiredConverters __attribute__((swift_name("getRequiredConverters()")));
@end

__attribute__((swift_name("PeopleDatabase")))
@interface ComposeAppPeopleDatabase : ComposeAppRoom_runtimeRoomDatabase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (id<ComposeAppPeopleDao>)peopleDao __attribute__((swift_name("peopleDao()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PeopleDatabase_Impl")))
@interface ComposeAppPeopleDatabase_Impl : ComposeAppPeopleDatabase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (NSArray<ComposeAppRoom_runtimeMigration *> *)createAutoMigrationsAutoMigrationSpecs:(NSDictionary<id<ComposeAppKotlinKClass>, id<ComposeAppRoom_runtimeAutoMigrationSpec>> *)autoMigrationSpecs __attribute__((swift_name("createAutoMigrations(autoMigrationSpecs:)")));

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (ComposeAppRoom_runtimeInvalidationTracker *)createInvalidationTracker __attribute__((swift_name("createInvalidationTracker()")));

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (ComposeAppRoom_runtimeRoomOpenDelegate *)createOpenDelegate __attribute__((swift_name("createOpenDelegate()")));
- (NSSet<id<ComposeAppKotlinKClass>> *)getRequiredAutoMigrationSpecClasses __attribute__((swift_name("getRequiredAutoMigrationSpecClasses()")));

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (NSDictionary<id<ComposeAppKotlinKClass>, NSArray<id<ComposeAppKotlinKClass>> *> *)getRequiredTypeConverterClasses __attribute__((swift_name("getRequiredTypeConverterClasses()")));
- (id<ComposeAppPeopleDao>)peopleDao __attribute__((swift_name("peopleDao()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Person")))
@interface ComposeAppPerson : ComposeAppBase
- (instancetype)initWithName:(NSString *)name id:(int32_t)id __attribute__((swift_name("init(name:id:)"))) __attribute__((objc_designated_initializer));
- (ComposeAppPerson *)doCopyName:(NSString *)name id:(int32_t)id __attribute__((swift_name("doCopy(name:id:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) int32_t id __attribute__((swift_name("id")));
@property (readonly) NSString *name __attribute__((swift_name("name")));
@end

__attribute__((swift_name("BookingDao")))
@protocol ComposeAppBookingDao
@required

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)deleteBooking:(ComposeAppBooking *)booking completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("delete(booking:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)getBookingByComputerDayTimeComputerId:(int32_t)computerId dayOfWeek:(NSString *)dayOfWeek timeSlot:(NSString *)timeSlot completionHandler:(void (^)(ComposeAppBooking * _Nullable_result, NSError * _Nullable))completionHandler __attribute__((swift_name("getBookingByComputerDayTime(computerId:dayOfWeek:timeSlot:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)getBookingsByDayAndTimeDayOfWeek:(NSString *)dayOfWeek timeSlot:(NSString *)timeSlot completionHandler:(void (^)(NSArray<ComposeAppBooking *> * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getBookingsByDayAndTime(dayOfWeek:timeSlot:completionHandler:)")));
- (id<ComposeAppKotlinx_coroutines_coreFlow>)getBookingsByRoomAndDayRoomId:(int32_t)roomId dayOfWeek:(NSString *)dayOfWeek __attribute__((swift_name("getBookingsByRoomAndDay(roomId:dayOfWeek:)")));
- (id<ComposeAppKotlinx_coroutines_coreFlow>)getBookingsByUserUserId:(int32_t)userId __attribute__((swift_name("getBookingsByUser(userId:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)upsertBooking:(ComposeAppBooking *)booking completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("upsert(booking:completionHandler:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BookingDao_Impl")))
@interface ComposeAppBookingDao_Impl : ComposeAppBase <ComposeAppBookingDao>
- (instancetype)initWith__db:(ComposeAppRoom_runtimeRoomDatabase *)__db __attribute__((swift_name("init(__db:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) ComposeAppBookingDao_ImplCompanion *companion __attribute__((swift_name("companion")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)deleteBooking:(ComposeAppBooking *)booking completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("delete(booking:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)getBookingByComputerDayTimeComputerId:(int32_t)computerId dayOfWeek:(NSString *)dayOfWeek timeSlot:(NSString *)timeSlot completionHandler:(void (^)(ComposeAppBooking * _Nullable_result, NSError * _Nullable))completionHandler __attribute__((swift_name("getBookingByComputerDayTime(computerId:dayOfWeek:timeSlot:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)getBookingsByDayAndTimeDayOfWeek:(NSString *)dayOfWeek timeSlot:(NSString *)timeSlot completionHandler:(void (^)(NSArray<ComposeAppBooking *> * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getBookingsByDayAndTime(dayOfWeek:timeSlot:completionHandler:)")));
- (id<ComposeAppKotlinx_coroutines_coreFlow>)getBookingsByRoomAndDayRoomId:(int32_t)roomId dayOfWeek:(NSString *)dayOfWeek __attribute__((swift_name("getBookingsByRoomAndDay(roomId:dayOfWeek:)")));
- (id<ComposeAppKotlinx_coroutines_coreFlow>)getBookingsByUserUserId:(int32_t)userId __attribute__((swift_name("getBookingsByUser(userId:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)upsertBooking:(ComposeAppBooking *)booking completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("upsert(booking:completionHandler:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BookingDao_Impl.Companion")))
@interface ComposeAppBookingDao_ImplCompanion : ComposeAppBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) ComposeAppBookingDao_ImplCompanion *shared __attribute__((swift_name("shared")));
- (NSArray<id<ComposeAppKotlinKClass>> *)getRequiredConverters __attribute__((swift_name("getRequiredConverters()")));
@end

__attribute__((swift_name("ComputerDao")))
@protocol ComposeAppComputerDao
@required

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)deleteComputer:(ComposeAppComputer *)computer completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("delete(computer:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)getComputerByIdComputerId:(int32_t)computerId completionHandler:(void (^)(ComposeAppComputer * _Nullable_result, NSError * _Nullable))completionHandler __attribute__((swift_name("getComputerById(computerId:completionHandler:)")));
- (id<ComposeAppKotlinx_coroutines_coreFlow>)getComputersByRoomRoomId:(int32_t)roomId __attribute__((swift_name("getComputersByRoom(roomId:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)upsertComputer:(ComposeAppComputer *)computer completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("upsert(computer:completionHandler:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ComputerDao_Impl")))
@interface ComposeAppComputerDao_Impl : ComposeAppBase <ComposeAppComputerDao>
- (instancetype)initWith__db:(ComposeAppRoom_runtimeRoomDatabase *)__db __attribute__((swift_name("init(__db:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) ComposeAppComputerDao_ImplCompanion *companion __attribute__((swift_name("companion")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)deleteComputer:(ComposeAppComputer *)computer completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("delete(computer:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)getComputerByIdComputerId:(int32_t)computerId completionHandler:(void (^)(ComposeAppComputer * _Nullable_result, NSError * _Nullable))completionHandler __attribute__((swift_name("getComputerById(computerId:completionHandler:)")));
- (id<ComposeAppKotlinx_coroutines_coreFlow>)getComputersByRoomRoomId:(int32_t)roomId __attribute__((swift_name("getComputersByRoom(roomId:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)upsertComputer:(ComposeAppComputer *)computer completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("upsert(computer:completionHandler:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ComputerDao_Impl.Companion")))
@interface ComposeAppComputerDao_ImplCompanion : ComposeAppBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) ComposeAppComputerDao_ImplCompanion *shared __attribute__((swift_name("shared")));
- (NSArray<id<ComposeAppKotlinKClass>> *)getRequiredConverters __attribute__((swift_name("getRequiredConverters()")));
@end

__attribute__((swift_name("RoomDao")))
@protocol ComposeAppRoomDao
@required

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)deleteRoom:(ComposeAppRoom *)room completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("delete(room:completionHandler:)")));
- (id<ComposeAppKotlinx_coroutines_coreFlow>)getAllRooms __attribute__((swift_name("getAllRooms()")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)getRoomByIdRoomId:(int32_t)roomId completionHandler:(void (^)(ComposeAppRoom * _Nullable_result, NSError * _Nullable))completionHandler __attribute__((swift_name("getRoomById(roomId:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)upsertRoom:(ComposeAppRoom *)room completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("upsert(room:completionHandler:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RoomDao_Impl")))
@interface ComposeAppRoomDao_Impl : ComposeAppBase <ComposeAppRoomDao>
- (instancetype)initWith__db:(ComposeAppRoom_runtimeRoomDatabase *)__db __attribute__((swift_name("init(__db:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) ComposeAppRoomDao_ImplCompanion *companion __attribute__((swift_name("companion")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)deleteRoom:(ComposeAppRoom *)room completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("delete(room:completionHandler:)")));
- (id<ComposeAppKotlinx_coroutines_coreFlow>)getAllRooms __attribute__((swift_name("getAllRooms()")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)getRoomByIdRoomId:(int32_t)roomId completionHandler:(void (^)(ComposeAppRoom * _Nullable_result, NSError * _Nullable))completionHandler __attribute__((swift_name("getRoomById(roomId:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)upsertRoom:(ComposeAppRoom *)room completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("upsert(room:completionHandler:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RoomDao_Impl.Companion")))
@interface ComposeAppRoomDao_ImplCompanion : ComposeAppBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) ComposeAppRoomDao_ImplCompanion *shared __attribute__((swift_name("shared")));
- (NSArray<id<ComposeAppKotlinKClass>> *)getRequiredConverters __attribute__((swift_name("getRequiredConverters()")));
@end

__attribute__((swift_name("UserDao")))
@protocol ComposeAppUserDao
@required

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)deleteUser:(ComposeAppUser *)user completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("delete(user:completionHandler:)")));
- (id<ComposeAppKotlinx_coroutines_coreFlow>)getAllUsers __attribute__((swift_name("getAllUsers()")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)getUserByEmailEmail:(NSString *)email completionHandler:(void (^)(ComposeAppUser * _Nullable_result, NSError * _Nullable))completionHandler __attribute__((swift_name("getUserByEmail(email:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)getUserByIdUserId:(int32_t)userId completionHandler:(void (^)(ComposeAppUser * _Nullable_result, NSError * _Nullable))completionHandler __attribute__((swift_name("getUserById(userId:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)loginEmail:(NSString *)email password:(NSString *)password completionHandler:(void (^)(ComposeAppUser * _Nullable_result, NSError * _Nullable))completionHandler __attribute__((swift_name("login(email:password:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)upsertUser:(ComposeAppUser *)user completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("upsert(user:completionHandler:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("UserDao_Impl")))
@interface ComposeAppUserDao_Impl : ComposeAppBase <ComposeAppUserDao>
- (instancetype)initWith__db:(ComposeAppRoom_runtimeRoomDatabase *)__db __attribute__((swift_name("init(__db:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) ComposeAppUserDao_ImplCompanion *companion __attribute__((swift_name("companion")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)deleteUser:(ComposeAppUser *)user completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("delete(user:completionHandler:)")));
- (id<ComposeAppKotlinx_coroutines_coreFlow>)getAllUsers __attribute__((swift_name("getAllUsers()")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)getUserByEmailEmail:(NSString *)email completionHandler:(void (^)(ComposeAppUser * _Nullable_result, NSError * _Nullable))completionHandler __attribute__((swift_name("getUserByEmail(email:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)getUserByIdUserId:(int32_t)userId completionHandler:(void (^)(ComposeAppUser * _Nullable_result, NSError * _Nullable))completionHandler __attribute__((swift_name("getUserById(userId:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)loginEmail:(NSString *)email password:(NSString *)password completionHandler:(void (^)(ComposeAppUser * _Nullable_result, NSError * _Nullable))completionHandler __attribute__((swift_name("login(email:password:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)upsertUser:(ComposeAppUser *)user completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("upsert(user:completionHandler:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("UserDao_Impl.Companion")))
@interface ComposeAppUserDao_ImplCompanion : ComposeAppBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) ComposeAppUserDao_ImplCompanion *shared __attribute__((swift_name("shared")));
- (NSArray<id<ComposeAppKotlinKClass>> *)getRequiredConverters __attribute__((swift_name("getRequiredConverters()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Booking")))
@interface ComposeAppBooking : ComposeAppBase
- (instancetype)initWithUserId:(int32_t)userId computerId:(int32_t)computerId roomId:(int32_t)roomId dayOfWeek:(NSString *)dayOfWeek timeSlot:(NSString *)timeSlot bookingDate:(NSString *)bookingDate id:(int32_t)id __attribute__((swift_name("init(userId:computerId:roomId:dayOfWeek:timeSlot:bookingDate:id:)"))) __attribute__((objc_designated_initializer));
- (ComposeAppBooking *)doCopyUserId:(int32_t)userId computerId:(int32_t)computerId roomId:(int32_t)roomId dayOfWeek:(NSString *)dayOfWeek timeSlot:(NSString *)timeSlot bookingDate:(NSString *)bookingDate id:(int32_t)id __attribute__((swift_name("doCopy(userId:computerId:roomId:dayOfWeek:timeSlot:bookingDate:id:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *bookingDate __attribute__((swift_name("bookingDate")));
@property (readonly) int32_t computerId __attribute__((swift_name("computerId")));
@property (readonly) NSString *dayOfWeek __attribute__((swift_name("dayOfWeek")));
@property (readonly) int32_t id __attribute__((swift_name("id")));
@property (readonly) int32_t roomId __attribute__((swift_name("roomId")));
@property (readonly) NSString *timeSlot __attribute__((swift_name("timeSlot")));
@property (readonly) int32_t userId __attribute__((swift_name("userId")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Computer")))
@interface ComposeAppComputer : ComposeAppBase
- (instancetype)initWithRoomId:(int32_t)roomId computerNumber:(int32_t)computerNumber globalId:(NSString *)globalId id:(int32_t)id __attribute__((swift_name("init(roomId:computerNumber:globalId:id:)"))) __attribute__((objc_designated_initializer));
- (ComposeAppComputer *)doCopyRoomId:(int32_t)roomId computerNumber:(int32_t)computerNumber globalId:(NSString *)globalId id:(int32_t)id __attribute__((swift_name("doCopy(roomId:computerNumber:globalId:id:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) int32_t computerNumber __attribute__((swift_name("computerNumber")));
@property (readonly) NSString *globalId __attribute__((swift_name("globalId")));
@property (readonly) int32_t id __attribute__((swift_name("id")));
@property (readonly) int32_t roomId __attribute__((swift_name("roomId")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Room")))
@interface ComposeAppRoom : ComposeAppBase
- (instancetype)initWithRoomNumber:(NSString *)roomNumber computerCount:(int32_t)computerCount id:(int32_t)id __attribute__((swift_name("init(roomNumber:computerCount:id:)"))) __attribute__((objc_designated_initializer));
- (ComposeAppRoom *)doCopyRoomNumber:(NSString *)roomNumber computerCount:(int32_t)computerCount id:(int32_t)id __attribute__((swift_name("doCopy(roomNumber:computerCount:id:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) int32_t computerCount __attribute__((swift_name("computerCount")));
@property (readonly) int32_t id __attribute__((swift_name("id")));
@property (readonly) NSString *roomNumber __attribute__((swift_name("roomNumber")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("User")))
@interface ComposeAppUser : ComposeAppBase
- (instancetype)initWithName:(NSString *)name email:(NSString *)email password:(NSString *)password contactDetails:(NSString *)contactDetails isAdmin:(BOOL)isAdmin id:(int32_t)id __attribute__((swift_name("init(name:email:password:contactDetails:isAdmin:id:)"))) __attribute__((objc_designated_initializer));
- (ComposeAppUser *)doCopyName:(NSString *)name email:(NSString *)email password:(NSString *)password contactDetails:(NSString *)contactDetails isAdmin:(BOOL)isAdmin id:(int32_t)id __attribute__((swift_name("doCopy(name:email:password:contactDetails:isAdmin:id:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *contactDetails __attribute__((swift_name("contactDetails")));
@property (readonly) NSString *email __attribute__((swift_name("email")));
@property (readonly) int32_t id __attribute__((swift_name("id")));
@property (readonly) BOOL isAdmin __attribute__((swift_name("isAdmin")));
@property (readonly) NSString *name __attribute__((swift_name("name")));
@property (readonly) NSString *password __attribute__((swift_name("password")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("MainViewControllerKt")))
@interface ComposeAppMainViewControllerKt : ComposeAppBase
+ (UIViewController *)MainViewController __attribute__((swift_name("MainViewController()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Platform_iosKt")))
@interface ComposeAppPlatform_iosKt : ComposeAppBase
+ (id<ComposeAppPlatform>)getPlatform __attribute__((swift_name("getPlatform()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("GetBookingDatabaseKt")))
@interface ComposeAppGetBookingDatabaseKt : ComposeAppBase
+ (ComposeAppBookingDatabase *)getBookingDatabase __attribute__((swift_name("getBookingDatabase()")));
@end

__attribute__((swift_name("Room_runtimeMigration")))
@interface ComposeAppRoom_runtimeMigration : ComposeAppBase
- (instancetype)initWithStartVersion:(int32_t)startVersion endVersion:(int32_t)endVersion __attribute__((swift_name("init(startVersion:endVersion:)"))) __attribute__((objc_designated_initializer));
- (void)migrateConnection:(id<ComposeAppSqliteSQLiteConnection>)connection __attribute__((swift_name("migrate(connection:)")));
@property (readonly) int32_t endVersion __attribute__((swift_name("endVersion")));
@property (readonly) int32_t startVersion __attribute__((swift_name("startVersion")));
@end

__attribute__((swift_name("KotlinKDeclarationContainer")))
@protocol ComposeAppKotlinKDeclarationContainer
@required
@end

__attribute__((swift_name("KotlinKAnnotatedElement")))
@protocol ComposeAppKotlinKAnnotatedElement
@required
@end


/**
 * @note annotations
 *   kotlin.SinceKotlin(version="1.1")
*/
__attribute__((swift_name("KotlinKClassifier")))
@protocol ComposeAppKotlinKClassifier
@required
@end

__attribute__((swift_name("KotlinKClass")))
@protocol ComposeAppKotlinKClass <ComposeAppKotlinKDeclarationContainer, ComposeAppKotlinKAnnotatedElement, ComposeAppKotlinKClassifier>
@required

/**
 * @note annotations
 *   kotlin.SinceKotlin(version="1.1")
*/
- (BOOL)isInstanceValue:(id _Nullable)value __attribute__((swift_name("isInstance(value:)")));
@property (readonly) NSString * _Nullable qualifiedName __attribute__((swift_name("qualifiedName")));
@property (readonly) NSString * _Nullable simpleName __attribute__((swift_name("simpleName")));
@end

__attribute__((swift_name("Room_runtimeAutoMigrationSpec")))
@protocol ComposeAppRoom_runtimeAutoMigrationSpec
@required
- (void)onPostMigrateConnection:(id<ComposeAppSqliteSQLiteConnection>)connection __attribute__((swift_name("onPostMigrate(connection:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Room_runtimeInvalidationTracker")))
@interface ComposeAppRoom_runtimeInvalidationTracker : ComposeAppBase

/**
 * @note annotations
 *   androidx.annotation.RestrictTo(value=[Scope.LIBRARY_GROUP_PREFIX])
*/
- (instancetype)initWithDatabase:(ComposeAppRoom_runtimeRoomDatabase *)database shadowTablesMap:(NSDictionary<NSString *, NSString *> *)shadowTablesMap viewTables:(NSDictionary<NSString *, NSSet<NSString *> *> *)viewTables tableNames:(ComposeAppKotlinArray<NSString *> *)tableNames __attribute__((swift_name("init(database:shadowTablesMap:viewTables:tableNames:)"))) __attribute__((objc_designated_initializer));
- (void)refreshAsync __attribute__((swift_name("refreshAsync()")));
- (void)stop __attribute__((swift_name("stop()")));

/**
 * @note annotations
 *   androidx.annotation.RestrictTo(value=[Scope.LIBRARY_GROUP])
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)subscribeObserver:(ComposeAppRoom_runtimeInvalidationTrackerObserver *)observer completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("subscribe(observer:completionHandler:)")));

/**
 * @note annotations
 *   androidx.annotation.RestrictTo(value=[Scope.LIBRARY_GROUP])
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)unsubscribeObserver:(ComposeAppRoom_runtimeInvalidationTrackerObserver *)observer completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("unsubscribe(observer:completionHandler:)")));
@end

__attribute__((swift_name("Room_runtimeRoomOpenDelegateMarker")))
@protocol ComposeAppRoom_runtimeRoomOpenDelegateMarker
@required
@end

__attribute__((swift_name("SqliteSQLiteConnection")))
@protocol ComposeAppSqliteSQLiteConnection
@required
- (void)close __attribute__((swift_name("close()")));
- (id<ComposeAppSqliteSQLiteStatement>)prepareSql:(NSString *)sql __attribute__((swift_name("prepare(sql:)")));
@end


/**
 * @note annotations
 *   androidx.annotation.RestrictTo(value=[Scope.LIBRARY_GROUP])
*/
__attribute__((swift_name("Room_runtimeRoomOpenDelegate")))
@interface ComposeAppRoom_runtimeRoomOpenDelegate : ComposeAppBase <ComposeAppRoom_runtimeRoomOpenDelegateMarker>
- (instancetype)initWithVersion:(int32_t)version identityHash:(NSString *)identityHash legacyIdentityHash:(NSString *)legacyIdentityHash __attribute__((swift_name("init(version:identityHash:legacyIdentityHash:)"))) __attribute__((objc_designated_initializer));
- (void)createAllTablesConnection:(id<ComposeAppSqliteSQLiteConnection>)connection __attribute__((swift_name("createAllTables(connection:)")));
- (void)dropAllTablesConnection:(id<ComposeAppSqliteSQLiteConnection>)connection __attribute__((swift_name("dropAllTables(connection:)")));
- (void)onCreateConnection:(id<ComposeAppSqliteSQLiteConnection>)connection __attribute__((swift_name("onCreate(connection:)")));
- (void)onOpenConnection:(id<ComposeAppSqliteSQLiteConnection>)connection __attribute__((swift_name("onOpen(connection:)")));
- (void)onPostMigrateConnection:(id<ComposeAppSqliteSQLiteConnection>)connection __attribute__((swift_name("onPostMigrate(connection:)")));
- (void)onPreMigrateConnection:(id<ComposeAppSqliteSQLiteConnection>)connection __attribute__((swift_name("onPreMigrate(connection:)")));
- (ComposeAppRoom_runtimeRoomOpenDelegateValidationResult *)onValidateSchemaConnection:(id<ComposeAppSqliteSQLiteConnection>)connection __attribute__((swift_name("onValidateSchema(connection:)")));
@property (readonly) NSString *identityHash __attribute__((swift_name("identityHash")));
@property (readonly) NSString *legacyIdentityHash __attribute__((swift_name("legacyIdentityHash")));
@property (readonly) int32_t version __attribute__((swift_name("version")));
@end

__attribute__((swift_name("KotlinThrowable")))
@interface ComposeAppKotlinThrowable : ComposeAppBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(ComposeAppKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(ComposeAppKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));

/**
 * @note annotations
 *   kotlin.experimental.ExperimentalNativeApi
*/
- (ComposeAppKotlinArray<NSString *> *)getStackTrace __attribute__((swift_name("getStackTrace()")));
- (void)printStackTrace __attribute__((swift_name("printStackTrace()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) ComposeAppKotlinThrowable * _Nullable cause __attribute__((swift_name("cause")));
@property (readonly) NSString * _Nullable message __attribute__((swift_name("message")));
- (NSError *)asError __attribute__((swift_name("asError()")));
@end

__attribute__((swift_name("KotlinException")))
@interface ComposeAppKotlinException : ComposeAppKotlinThrowable
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(ComposeAppKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(ComposeAppKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
@end

__attribute__((swift_name("KotlinRuntimeException")))
@interface ComposeAppKotlinRuntimeException : ComposeAppKotlinException
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(ComposeAppKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(ComposeAppKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
@end

__attribute__((swift_name("KotlinIllegalStateException")))
@interface ComposeAppKotlinIllegalStateException : ComposeAppKotlinRuntimeException
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(ComposeAppKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(ComposeAppKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
@end


/**
 * @note annotations
 *   kotlin.SinceKotlin(version="1.4")
*/
__attribute__((swift_name("KotlinCancellationException")))
@interface ComposeAppKotlinCancellationException : ComposeAppKotlinIllegalStateException
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(ComposeAppKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(ComposeAppKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
@end

__attribute__((swift_name("Kotlinx_coroutines_coreFlow")))
@protocol ComposeAppKotlinx_coroutines_coreFlow
@required

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)collectCollector:(id<ComposeAppKotlinx_coroutines_coreFlowCollector>)collector completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("collect(collector:completionHandler:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinArray")))
@interface ComposeAppKotlinArray<T> : ComposeAppBase
+ (instancetype)arrayWithSize:(int32_t)size init:(T _Nullable (^)(ComposeAppInt *))init __attribute__((swift_name("init(size:init:)")));
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (T _Nullable)getIndex:(int32_t)index __attribute__((swift_name("get(index:)")));
- (id<ComposeAppKotlinIterator>)iterator __attribute__((swift_name("iterator()")));
- (void)setIndex:(int32_t)index value:(T _Nullable)value __attribute__((swift_name("set(index:value:)")));
@property (readonly) int32_t size __attribute__((swift_name("size")));
@end

__attribute__((swift_name("Room_runtimeInvalidationTracker.Observer")))
@interface ComposeAppRoom_runtimeInvalidationTrackerObserver : ComposeAppBase
- (instancetype)initWithTables:(ComposeAppKotlinArray<NSString *> *)tables __attribute__((swift_name("init(tables:)"))) __attribute__((objc_designated_initializer));

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (instancetype)initWithFirstTable:(NSString *)firstTable rest:(ComposeAppKotlinArray<NSString *> *)rest __attribute__((swift_name("init(firstTable:rest:)"))) __attribute__((objc_designated_initializer));
- (void)onInvalidatedTables:(NSSet<NSString *> *)tables __attribute__((swift_name("onInvalidated(tables:)")));
@end

__attribute__((swift_name("SqliteSQLiteStatement")))
@protocol ComposeAppSqliteSQLiteStatement
@required
- (void)bindBlobIndex:(int32_t)index value:(ComposeAppKotlinByteArray *)value __attribute__((swift_name("bindBlob(index:value:)")));
- (void)bindBooleanIndex:(int32_t)index value:(BOOL)value __attribute__((swift_name("bindBoolean(index:value:)")));
- (void)bindDoubleIndex:(int32_t)index value:(double)value __attribute__((swift_name("bindDouble(index:value:)")));
- (void)bindFloatIndex:(int32_t)index value:(float)value __attribute__((swift_name("bindFloat(index:value:)")));
- (void)bindIntIndex:(int32_t)index value:(int32_t)value __attribute__((swift_name("bindInt(index:value:)")));
- (void)bindLongIndex:(int32_t)index value:(int64_t)value __attribute__((swift_name("bindLong(index:value:)")));
- (void)bindNullIndex:(int32_t)index __attribute__((swift_name("bindNull(index:)")));
- (void)bindTextIndex:(int32_t)index value:(NSString *)value __attribute__((swift_name("bindText(index:value:)")));
- (void)clearBindings __attribute__((swift_name("clearBindings()")));
- (void)close __attribute__((swift_name("close()")));
- (ComposeAppKotlinByteArray *)getBlobIndex:(int32_t)index __attribute__((swift_name("getBlob(index:)")));
- (BOOL)getBooleanIndex:(int32_t)index __attribute__((swift_name("getBoolean(index:)")));
- (int32_t)getColumnCount __attribute__((swift_name("getColumnCount()")));
- (NSString *)getColumnNameIndex:(int32_t)index __attribute__((swift_name("getColumnName(index:)")));
- (NSArray<NSString *> *)getColumnNames __attribute__((swift_name("getColumnNames()")));
- (double)getDoubleIndex:(int32_t)index __attribute__((swift_name("getDouble(index:)")));
- (float)getFloatIndex:(int32_t)index __attribute__((swift_name("getFloat(index:)")));
- (int32_t)getIntIndex:(int32_t)index __attribute__((swift_name("getInt(index:)")));
- (int64_t)getLongIndex:(int32_t)index __attribute__((swift_name("getLong(index:)")));
- (NSString *)getTextIndex:(int32_t)index __attribute__((swift_name("getText(index:)")));
- (BOOL)isNullIndex:(int32_t)index __attribute__((swift_name("isNull(index:)")));
- (void)reset __attribute__((swift_name("reset()")));
- (BOOL)step __attribute__((swift_name("step()")));
@end


/**
 * @note annotations
 *   androidx.annotation.RestrictTo(value=[Scope.LIBRARY_GROUP])
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Room_runtimeRoomOpenDelegate.ValidationResult")))
@interface ComposeAppRoom_runtimeRoomOpenDelegateValidationResult : ComposeAppBase
- (instancetype)initWithIsValid:(BOOL)isValid expectedFoundMsg:(NSString * _Nullable)expectedFoundMsg __attribute__((swift_name("init(isValid:expectedFoundMsg:)"))) __attribute__((objc_designated_initializer));
@property (readonly) NSString * _Nullable expectedFoundMsg __attribute__((swift_name("expectedFoundMsg")));
@property (readonly) BOOL isValid __attribute__((swift_name("isValid")));
@end

__attribute__((swift_name("Kotlinx_coroutines_coreFlowCollector")))
@protocol ComposeAppKotlinx_coroutines_coreFlowCollector
@required

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)emitValue:(id _Nullable)value completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("emit(value:completionHandler:)")));
@end

__attribute__((swift_name("KotlinIterator")))
@protocol ComposeAppKotlinIterator
@required
- (BOOL)hasNext __attribute__((swift_name("hasNext()")));
- (id _Nullable)next __attribute__((swift_name("next()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinByteArray")))
@interface ComposeAppKotlinByteArray : ComposeAppBase
+ (instancetype)arrayWithSize:(int32_t)size __attribute__((swift_name("init(size:)")));
+ (instancetype)arrayWithSize:(int32_t)size init:(ComposeAppByte *(^)(ComposeAppInt *))init __attribute__((swift_name("init(size:init:)")));
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (int8_t)getIndex:(int32_t)index __attribute__((swift_name("get(index:)")));
- (ComposeAppKotlinByteIterator *)iterator __attribute__((swift_name("iterator()")));
- (void)setIndex:(int32_t)index value:(int8_t)value __attribute__((swift_name("set(index:value:)")));
@property (readonly) int32_t size __attribute__((swift_name("size")));
@end

__attribute__((swift_name("KotlinByteIterator")))
@interface ComposeAppKotlinByteIterator : ComposeAppBase <ComposeAppKotlinIterator>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (ComposeAppByte *)next __attribute__((swift_name("next()")));
- (int8_t)nextByte __attribute__((swift_name("nextByte()")));
@end

#pragma pop_macro("_Nullable_result")
#pragma clang diagnostic pop
NS_ASSUME_NONNULL_END
